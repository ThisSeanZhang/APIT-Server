package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.repository.APIRepository;
import io.whileaway.apit.api.entity.Node;
import io.whileaway.apit.api.specs.APISpec;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.utils.DataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class APIServiceImpl implements APIService {

    private final APIRepository apiRepository;
    private final NodeService nodeService;

    @Autowired
    public APIServiceImpl(APIRepository apiRepository, NodeService nodeService) {
        this.apiRepository = apiRepository;
        this.nodeService = nodeService;
    }

    @Override
    public Result<List<Node>> findByBelongFolder(Long belongFolder) {
        return new Spec<API, Node> ()
                .appendCondition(APISpec.belongFolder(()->belongFolder))
                .findInDB(apiRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<API> findById(Long aid) {
        return new DataBuilder<Long, API, API>(aid)
                .inspectParam(Objects::isNull)
                .findInDB(apiRepository::findByAid)
                .doNothing();
    }

    @Override
    @Transactional
    public Result<API> createAPI(API api) {
        API data = apiRepository.save(api);
        nodeService.createNode(new Node(data));
        return ResultUtil.success(ControllerEnum.SUCCESS, data);
    }
}
