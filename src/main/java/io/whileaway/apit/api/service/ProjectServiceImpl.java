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
import java.util.function.BiFunction;
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
                .findInDB(projectRepository::checkAllObtainProject)
                .doNothing();
    }

    @Override
    public Result<List<Node>> firstLayerContent(Long belongProject) {
        return folderService.firstLayerFolders(belongProject);
    }

    @Override
    public boolean inspectPermission(HttpServletRequest request, Long projectId, BiFunction<Project, Long, Boolean> check) {
        Optional<Developer> developer = Optional.ofNullable((Developer) request.getSession().getAttribute("currentDeveloper"));
        Project project = getProject(projectId);
        Long developerId = developer.map(Developer::getDeveloperId).orElse(null);
        if( check.apply(project, developerId) ) {
            return true;
        }
        else if (project.getOvert()) {
            throw new CommonException(ControllerEnum.NOT_ALLOW);
        }else{
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
    }

    @Override
    public boolean checkAllowDelete(Project project, Long developerId) {
        return Objects.equals(project.getProjectOwner(), developerId);
    }

    @Override
    public boolean checkAllowModify(Project project, Long developerId) {
        String patten = developerId + ",.*|.*,"+ developerId +",.*|.*," + developerId ;
        return Objects.nonNull(developerId) && project.getWhoJoins().matches(patten);
    }

    @Override
    public boolean checkAllowView(Project project, Long developerId) {
        return project.getOvert() || checkAllowModify(project, developerId);
    }

    @Override
    public Project getProject(Long pid) {
        Optional<Project> project = projectRepository.findById(pid);
        if (project.isEmpty()) throw new CommonException(ControllerEnum.NOT_FOUND);
        return project.get();
    }
}
