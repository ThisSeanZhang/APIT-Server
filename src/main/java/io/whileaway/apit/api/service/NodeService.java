package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Node;
import io.whileaway.apit.api.request.FilterNode;
import io.whileaway.apit.base.Result;

import java.util.List;

public interface NodeService {
    Result<List<Node>> firstLayerNodes(Long belongProject, Long ownerId);

    Result<List<Node>> nodeContent(FilterNode filterNode);

    Node createNode(Node node);
}
