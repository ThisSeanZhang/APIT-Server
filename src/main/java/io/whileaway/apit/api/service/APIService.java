package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import java.util.List;

public interface APIService {

    Result<List<Node>> findByBelongFolder(Long belongFolder);

    Result<List<Node>> findFirstLayerByProjectId(Long belongProject);

    Result<API> findById(Long aid);

    Result<API> createAPI(API api);

    Result<API> updateApi(API updateApi);

    API getById(Long aid);

    Result<API> delApi(Long aid);
}
