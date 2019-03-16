package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.enums.error.FolderError;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.request.CreateFolder;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.request.ModifyFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.utils.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("projects/{pid}/folders")
public class FolderController {

    private final FolderService folderService;
    private final ProjectService projectService;
    private final ValidUtil validUtil;

    @Autowired
    public FolderController(FolderService folderService, ProjectService projectService, ValidUtil validUtil) {
        this.folderService = folderService;
        this.projectService = projectService;
        this.validUtil = validUtil;
    }

    @GetMapping("/{fid}/content")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<List<Node>> filterFolders(@PathVariable("fid") Long fid, FilterFolder filterFolder) {
        filterFolder.setParentId(fid);
        return ResultUtil.checkList(folderService.folderContent(filterFolder),new CommonException(ControllerEnum.NOT_FOUND));
    }

    @GetMapping("/{fid}/sub_folders")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<List<Node>> subFolders(@PathVariable("fid") Long fid, FilterFolder filterFolder) {
        filterFolder.setParentId(fid);
        return ResultUtil.checkList(folderService.filterFolders(filterFolder),new CommonException(ControllerEnum.NOT_FOUND));
    }

    @GetMapping("/{fid}")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<Folder> getFolder(@PathVariable("fid") Long fid) {
        return folderService.findFolderById(fid);
    }

    @PostMapping()
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<Folder> createFolder (@PathVariable("pid") Long pid,@Valid @RequestBody CreateFolder createFolder, BindingResult bindingResult) {
        if (Objects.isNull(pid)) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        ResultUtil.inspect(bindingResult);
        createFolder.setBelongProject(pid);
        return ResultUtil.success(folderService.createFolder(createFolder.convertToFolder()));
    }

    @PostMapping("/{fid}/sub_folders")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<Folder> createSubFolder (
            @PathVariable("pid") Long pid,
            @PathVariable("fid") Long fid,
            @Valid @RequestBody CreateFolder createFolder,
            BindingResult bindingResult) {
        if (Objects.isNull(pid) || Objects.isNull(fid)) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        ResultUtil.inspect(bindingResult);
        createFolder.setBelongProject(pid);
        createFolder.setParentId(fid);
        return ResultUtil.success(folderService.createFolder(createFolder.convertToFolder()));
    }

    @PutMapping("/{fid}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<Folder> modifyFolder (@PathVariable("fid") Long fid,
                                        @Valid @RequestBody ModifyFolder modifyFolder,
                                        BindingResult bindingResult) {
        if (Objects.isNull(fid)) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        if (fid.equals(modifyFolder.getParentId())) throw new CommonException(FolderError.NOT_ALLOW_SELF_FATHER);
        ResultUtil.inspect(bindingResult);
        modifyFolder.setFid(fid);
        projectService.inspectPermission(
                validUtil.getCurrentDeveloper().getDeveloperId(),
                modifyFolder.getBelongProject(),
                projectService::checkAllowModifyContent);
        return folderService.modifyFolder(modifyFolder.convertToFolder());
    }

    @DeleteMapping("/{fid}")
    @CheckProjectPermission(PermissionType.DELETE)
    public Result<Folder> modifyFolder (@PathVariable("fid") Long fid) {
        return folderService.deleteFolder(fid);
    }

}
