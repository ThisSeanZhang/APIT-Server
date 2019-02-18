package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProjectService {

    Result<Project> createProject (Project project);

    Result<List<Project>> getProjectsByOwnerId (Long projectOwner);

    Result<List<Node>> firstLayerContent (Long belongProject, Long ownerId);

    void inspectPermission(HttpServletRequest request, Long projectId);
}
