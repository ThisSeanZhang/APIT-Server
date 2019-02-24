package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.BiFunction;

public interface ProjectService {

    Result<Project> createProject (Project project);

    Result<List<Project>> getProjectsByOwnerId (Long projectOwner);

    Result<List<Node>> firstLayerContent (Long belongProject);

    boolean inspectPermission(Long developerId, Long projectId, BiFunction<Project, Long, Boolean> check);

    boolean checkAllowModifyProject(Project project, Long checkAllowModifyProject);

    boolean checkAllowModifyContent(Project project, Long developerId);

    boolean checkAllowView(Project project, Long id);

    Project getProject(Long pid);

    Result<List<Node>> firstLayerFolder(Long pid);
}
