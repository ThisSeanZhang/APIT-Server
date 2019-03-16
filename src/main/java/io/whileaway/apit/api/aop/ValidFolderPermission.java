package io.whileaway.apit.api.aop;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.api.annotation.ValidFolder;
import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.utils.ValidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ValidFolderPermission {

    private final ProjectService projectService;
    private final FolderService folderService;
    private final ValidUtil validUtil;

    @Autowired
    public ValidFolderPermission(ProjectService projectService, FolderService folderService, ValidUtil validUtil) {
        this.projectService = projectService;
        this.folderService = folderService;
        this.validUtil = validUtil;
    }

    @Before(value = "@annotation(validFolder)", argNames = "joinPoint, validFolder")
    public void beforeCheck(JoinPoint joinPoint, ValidFolder validFolder) {
        System.out.println(joinPoint.getSignature());
        System.out.println("AspectJ" + Arrays.toString(joinPoint.getArgs()));
        Developer developer = validUtil.getCurrentDeveloper();
        Long fid = validUtil.getURITempleVariables("fid");
        Folder folder = folderService.getFolder(fid);
        System.out.println("pid为" + folder.getBelongProject());
        switch (validFolder.value()){
            case VIEW:
                projectService.inspectPermission(developer.getDeveloperId(), folder.getBelongProject(), projectService::checkAllowView);
                break;
            case DELETE:
                projectService.inspectPermission(developer.getDeveloperId(), folder.getBelongProject(), projectService::checkAllowModifyProject);
                break;
            case MODIFY:
                projectService.inspectPermission(developer.getDeveloperId(), folder.getBelongProject(), projectService::checkAllowModifyContent);
                break;
        }
    }
}