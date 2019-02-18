package io.whileaway.apit.api.service;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.repository.ProjectRepository;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.utils.DatasBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final FolderService folderService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, FolderService folderService) {
        this.projectRepository = projectRepository;
        this.folderService = folderService;
    }

    @Override
    public Result<Project> createProject(Project project) {
        return ResultUtil.success(ControllerEnum.SUCCESS, projectRepository.save(project));
    }

    @Override
    public Result<List<Project>> getProjectsByOwnerId(Long projectOwner) {
        return new DatasBuilder<Long, Project, Project>(projectOwner)
                .inspectParam(Objects::isNull)
                .findInDB(projectRepository::findByProjectOwner)
                .doNothing();
    }

    @Override
    public Result<List<Node>> firstLayerContent(Long belongProject, Long ownerId) {
        return folderService.firstLayerFolders(belongProject, ownerId);
    }

    @Override
    public void inspectPermission(HttpServletRequest request, Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) throw new CommonException(ControllerEnum.NOT_FOUND);

        if ( request.getSession() == null  ) throw new CommonException(ControllerEnum.SERVER_ERROR);
        Developer developer = (Developer) request.getSession().getAttribute("currentDeveloper");
        if (developer == null) throw new CommonException(ControllerEnum.NOT_ALLOW);

        if (Stream.of(project.get().getWhoJoins().split(",")).noneMatch(id -> developer.getDeveloperId().equals(Long.valueOf(id))))
            throw new CommonException(ControllerEnum.NOT_ALLOW);
    }
}
