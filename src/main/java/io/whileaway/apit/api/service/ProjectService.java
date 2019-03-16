package io.whileaway.apit.api.service;

import io.whileaway.apit.api.response.ProjectVO;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.request.FilterProject;
import io.whileaway.apit.api.request.ModifyProject;
import io.whileaway.apit.api.response.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.BiFunction;

public interface ProjectService {

    Project createProject (Project project);

    List<Project> getProjectsByOwnerId (Long projectOwner);

    List<Project> filterProject(FilterProject filterProject);

    List<Node> firstLayerContent (Long belongProject);

    boolean inspectPermission(Long developerId, Long projectId, BiFunction<Project, Long, Boolean> check);

    boolean checkAllowModifyProject(Project project, Long checkAllowModifyProject);

    boolean checkAllowModifyContent(Project project, Long developerId);

    boolean checkAllowView(Project project, Long id);

    Project getProject(Long pid);

    List<Node> firstLayerFolder(Long pid);

    List<Long> getWhoJoins(Long pid);

    Project modifyProject(ModifyProject modifyProject);

    Page<ProjectVO> adminFilterFind(FilterProject filterProject, Pageable pageable);

    void deleteProject(Long pid);
}
