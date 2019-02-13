package io.whileaway.apit.api.controller;

import io.whileaway.apit.api.entity.Node;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.request.FilterNode;
import io.whileaway.apit.api.service.NodeService;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    private final NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    //    @GetMapping("/first-layer/")
//    public Result<List<Node>> firstLayerNodesForProject (@RequestParam("belongProject") Long belongProject, @RequestParam("ownerId") Long folderOwnerId) {
//        if (belongProject == null || folderOwnerId == null) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
//        return folderService.firstLayerFolders(belongProject, folderOwnerId);
//    }
//
    @GetMapping("/content")
    public Result<List<Node>> filterNodes (FilterNode filterNode) {
        return nodeService.folderContent(filterNode);
    }
}