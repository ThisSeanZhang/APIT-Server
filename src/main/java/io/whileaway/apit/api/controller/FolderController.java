package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.PermissionType;
import io.whileaway.apit.api.annotation.CheckProjectPermission;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.api.service.ProjectService;
import io.whileaway.apit.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("projects/{pid}/folders")
public class FolderController {

    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }
//
//    @GetMapping("/belong-project/{id}")
//    public Result<List<Node>> getFoldersNodeByProjectId (@PathVariable("id") Long pid) {
//        return folderService.getFoldersNodeByProjectId(pid);
//    }
//
//    @GetMapping("/first-layer")
//    public Result<List<Node>> firstLayerFolders (@RequestParam("belongProject") Long belongProject, @RequestParam("folderOwnerId") Long folderOwnerId) {
//        if (belongProject == null || folderOwnerId == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
//        return folderService.firstLayerFolders(belongProject, folderOwnerId);
//    }
//
    @GetMapping("/{fid}/content")
    @CheckProjectPermission(PermissionType.VIEW)
    public Result<List<Node>> filterFolders(@PathVariable("fid") Long fid, FilterFolder filterFolder) {
        filterFolder.setParentId(fid);
        return folderService.folderContent(filterFolder);
    }
}
