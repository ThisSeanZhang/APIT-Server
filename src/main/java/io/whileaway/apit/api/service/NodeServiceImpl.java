package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Node;
import io.whileaway.apit.api.repository.NodeRepository;
import io.whileaway.apit.api.request.FilterNode;
import io.whileaway.apit.api.specs.NodeSpec;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.utils.DatasBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;

    @Autowired
    public NodeServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Result<List<Node>> firstLayerNodes(Long belongProject, Long ownerId) {
        return new Spec<Node, Node>()
                .appendCondition(NodeSpec.belongProject(belongProject))
                .appendCondition(NodeSpec.ownerId(ownerId))
                .appendCondition(NodeSpec.parentIdIsNull())
                .findInDB(nodeRepository::findAll)
                .doNothing();
    }

    @Override
    public Result<List<Node>> folderContent(FilterNode filterNode) {
        return new Spec<Node, Node>()
                .appendCondition(NodeSpec.belongProject(filterNode::getBelongProject))
                .appendCondition(NodeSpec.ownerId(filterNode::getOwnerId))
                .appendCondition(NodeSpec.parentId(filterNode::getParentId))
                .findInDB(nodeRepository::findAll)
                .doNothing();
    }

    @Override
    public Node createNode(Node node) {
        return nodeRepository.save(node);
    }
}
