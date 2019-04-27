package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.enums.StatusDict;
import io.whileaway.apit.api.repository.ProjectRepository;
import io.whileaway.apit.api.request.FilterProject;
import io.whileaway.apit.api.request.ModifyProject;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.response.ProjectVO;
import io.whileaway.apit.api.specs.ProjectSpec;
import io.whileaway.apit.base.*;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.utils.DatasBuilder;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final FolderService folderService;
    private final APIService apiService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, FolderService folderService, APIService apiService) {
        this.projectRepository = projectRepository;
        this.folderService = folderService;
        this.apiService = apiService;
    }

    @Override
    public Project createProject(Project project) {
        project.setWhoJoins(getWhoJoins(project.getProjectOwner(), project.getWhoJoins()));
        return projectRepository.save(project);
    }



    @Override
    public List<Project> getProjectsByOwnerId(Long projectOwner) {
        return new DatasBuilder<Long, Project, Project>(projectOwner)
                .inspectParam(Objects::isNull)
                .findInDB(projectRepository::checkAllObtainProject)
                .original();
    }

    @Override
    public List<Project> filterProject(FilterProject filterProject) {
        return new Spec<Project, ProjectVO>()
                .appendCondition(ProjectSpec.likeProjectName(filterProject::getProjectName))
                .appendCondition(ProjectSpec.projectOwner(filterProject::getOwner))
                .appendCondition(ProjectSpec.whoIsJoins(filterProject::getWhoJoin))
                .appendCondition(ProjectSpec.isOvert(filterProject::getOvert))
                .appendCondition(ProjectSpec.statusNormal())
                .findInDBUnCheck(projectRepository::findAll)
                .original();
    }

    @Override
    public List<Node> firstLayerContent(Long belongProject) {
        List<Node> firstLayerFolders = folderService.firstLayer(belongProject);
        List<Node> firstLayerApis = apiService.findFirstLayer(belongProject);
        firstLayerFolders.addAll(firstLayerApis);
        firstLayerFolders.forEach(System.out::println);
        return firstLayerFolders;
    }

    @Override
    public boolean inspectPermission(Long developerId, Long projectId, BiFunction<Project, Long, Boolean> check) {
//        Optional<Developer> developer = Optional.ofNullable((Developer) request.getSession().getAttribute("currentDeveloper"));
//        Long developerId = developer.map(Developer::getDeveloperId).orElse(null);
        Project project = getProject(projectId);
        System.out.println(project);
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
    public boolean checkAllowModifyProject(Project project, Long developerId) {
        boolean b = Objects.nonNull(developerId) && Objects.equals(project.getProjectOwner(), developerId);
        System.out.println("Is Allow developer" + developerId + " Modify Project:" + b + "for project " + project.getPid());
        return b;
    }

    @Override
    public boolean checkAllowModifyContent(Project project, Long developerId) {
        String patten = "^(.*,)?" + developerId + "(,.*)?$";
        boolean b = Objects.nonNull(developerId) && project.getWhoJoins().matches(patten) || checkAllowModifyProject(project, developerId);
        System.out.println("Is Allow developer" + developerId + " Modify Content:" + b + "for project " + project.getPid());
        return b;
    }

    @Override
    public boolean checkAllowView(Project project, Long developerId) {
        boolean b = project.getOvert() || checkAllowModifyContent(project, developerId);
        System.out.println("Is Allow developer" + developerId + " View Project:" + b + "for project " + project.getPid());
        return b;
    }

    @Override
    public Project getProject(Long pid) {
        return projectRepository.findByPidAndStatus(pid, StatusDict.NORMAL.getCode())
                .orElseThrow( () -> new CommonException(ControllerEnum.NOT_FOUND));
    }

    @Override
    public List<Node> firstLayerFolder(Long pid) {
        return folderService.firstLayer(pid);
    }

    @Override
    public List<Long> getWhoJoins(Long pid) {
        Project project = getProject(pid);
        return Stream.of(project.getWhoJoins().split(",")).map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public Project modifyProject(ModifyProject modifyProject) {
        Project data = getProject(modifyProject.getPid());
        data.setProjectName(modifyProject.getProjectName());
        data.setOvert(modifyProject.getOvert());
        data.setWhoJoins(getWhoJoins(data, modifyProject));

        return projectRepository.save(data);
    }

    @Override
    public Page<ProjectVO> adminFilterFind(FilterProject filterProject, Pageable pageable) {
        return new PageSpec<Project, ProjectVO>(pageable)
                .appendCondition(ProjectSpec.likeProjectName(filterProject::getProjectName))
                .appendCondition(ProjectSpec.projectOwner(filterProject::getOwner))
                .appendCondition(ProjectSpec.isOvert(filterProject::getOvert))
                .appendCondition(ProjectSpec.statusNormal())
                .findInDB(projectRepository::findAll)
                .convertOtherPage(ProjectVO::new);
    }

    @Override
    public void deleteProject(Long pid) {
        Project project = getProject(pid);
        project.setStatus(StatusDict.DELETE.getCode());
        projectRepository.save(project);
    }

    @Override
    public void leafProject(Long did, Long pid) {
        Project project = getProject(pid);
        project.setWhoJoins(developerLeafProject(project,did));
        projectRepository.save(project);
    }

    //    public static void main (String [] args) {
//        List<String> strings = List.of("1", "1,", "1,11,12", "11,1,12", "11,12,1", "11,11,11");
//        String patten = "^(.*,)?" + 12 + "(,.*)?$";
//        strings.stream().filter(s -> s.matches(patten))
//                .forEach(System.out::println);
//    }

    private String developerLeafProject(Project data, Long did) {
        return Stream.of(data.getWhoJoins().split(","))
                .filter(id -> !String.valueOf(did).equals(id))
                .distinct()
                .collect(Collectors.joining(","));
    }
    private String getWhoJoins(Project data, ModifyProject modify) {
        return getWhoJoins(data.getProjectOwner(), modify.getWhoJoins());
    }

    private String getWhoJoins(Long ownerId, String whoJoin) {
        if (StringUtils.isEmptyOrBlank(whoJoin))
            return String.valueOf(ownerId);
        String str = Stream.of(whoJoin.split(","))
                .filter(id -> !String.valueOf(ownerId).equals(id))
                .distinct()
                .collect(Collectors.joining(","));
        return StringUtils.isEmptyOrBlank(str) ? String.valueOf(ownerId) :  ownerId + "," + str;
    }
}
