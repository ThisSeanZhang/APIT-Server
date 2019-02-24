package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.request.CreateFolder;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.request.ModifyFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("projects/{pid}/folders")
public class FolderController {

    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/{fid}/content")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<List<Node>> filterFolders(@PathVariable("fid") Long fid, FilterFolder filterFolder) {
        filterFolder.setParentId(fid);
        return folderService.folderContent(filterFolder);
    }
    @GetMapping("/{fid}/sub-folder")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<List<Node>> subFolders(@PathVariable("fid") Long fid, FilterFolder filterFolder) {
        filterFolder.setParentId(fid);
        return folderService.subFolders(filterFolder);
    }

    @GetMapping("/{fid}")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<Folder> getFolder(@PathVariable("fid") Long fid) {
        return folderService.findFolderById(fid);
    }

    @PostMapping()
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<Folder> createFolder (@PathVariable("pid") Long pid,@Valid @RequestBody CreateFolder createFolder, BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        createFolder.setBelongProject(pid);
        return folderService.createFolder(createFolder.convertToFolder());
    }

    @PutMapping("/{fid}")
    @CheckProjectPermission(PermissionType.MODIFY)
    public Result<Folder> modifyFolder (@PathVariable("pid") Long pid,
                                        @PathVariable("fid") Long fid,
                                        @Valid @RequestBody ModifyFolder modifyFolder,
                                        BindingResult bindingResult) {
        ResultUtil.inspect(bindingResult);
        modifyFolder.setBelongProject(pid);
        modifyFolder.setFid(fid);
        return folderService.modifyFolder(modifyFolder.convertToFolder());
    }

    @DeleteMapping("/{fid}")
    @CheckProjectPermission(PermissionType.DELETE)
    public Result<Folder> modifyFolder (@PathVariable("fid") Long fid) {
        return folderService.deleteFolder(fid);
    }

}
