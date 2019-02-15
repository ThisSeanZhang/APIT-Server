package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.service.FolderService;
import io.whileaway.apit.base.Result;
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

    @GetMapping("/{folderId}/content")
    public Result<List<Node>> filterFolders (@PathVariable("folderId") Long folderId, FilterFolder filterFolder) {
        filterFolder.setParentId(folderId);
        return folderService.folderContent(filterFolder);
    }
}
