package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.repository.APIRepository;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.specs.APISpec;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
