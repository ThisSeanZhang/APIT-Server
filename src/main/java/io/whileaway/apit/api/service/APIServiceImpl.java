package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.repository.APIRepository;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.specs.APISpec;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.utils.DataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class APIServiceImpl implements APIService {

    private final APIRepository apiRepository;

    @Autowired
    public APIServiceImpl(APIRepository apiRepository) {
        this.apiRepository = apiRepository;
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
}
