package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.repository.ProjectRepository;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.utils.DatasBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Result<List<Project>> getProjectsByOwnerId(Long projectOwner) {
        return new DatasBuilder<Long, Project, Project>(projectOwner)
                .inspectParam(Objects::isNull)
                .findInDB(projectRepository::findByProjectOwner)
                .doNothing();
    }

    @Override
    public Result<List<Node>> firstLayerContent(Long belongProject, Long folderOwnerId) {
        return folderService.firstLayerFolders(belongProject, folderOwnerId);
    }


}
