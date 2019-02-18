package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface APIService {

    Result<List<Node>> findByBelongFolder(Long belongFolder);

    Result<API> findById(Long aid);

    Result<API> createAPI(API api);

    Result<API> updateApi(API updateApi);

    API getById(Long aid);

    void inspectPermission(HttpServletRequest request, Long aid);

    void checkProjectOvert(Long id);
}
