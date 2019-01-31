package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/belong-project/{id}")
    public Result<List<Node>> getFoldersNodeByProjectId (@PathVariable("id") Long pid) {
        return folderService.getFoldersNodeByProjectId(pid);
    }

    @GetMapping("/first-layer")
    public Result<List<Node>> firstLayerFolders (@RequestParam("belongProject") Long belongProject, @RequestParam("folderOwnerId") Long folderOwnerId) {
        if (belongProject == null || folderOwnerId == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return folderService.firstLayerFolders(belongProject, folderOwnerId);
    }

    @GetMapping()
    public Result<List<Node>> filterFolders (FilterFolder FilterFolder) {
        return folderService.filterFolders(FilterFolder);
    }
}
