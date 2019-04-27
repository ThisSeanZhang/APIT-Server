package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.request.EditAPIResponseExample;
import io.whileaway.apit.api.request.EditAPIResponseParams;
import io.whileaway.apit.api.request.LocationRequest;
import io.whileaway.apit.api.response.Node;

import java.util.List;

public interface APIService {

    List<Node> findByBelongFolder(Long belongFolder);

    List<Node> findFirstLayer(Long belongProject);

    API findById(Long aid);

    API createAPI(API api);

    API updateApi(API updateApi);

    List<API> getByBelongFolder(Long fid);

    void saveAll(List<API> apis);

    API getApi(Long aid);

    void delApi(Long aid);

    void moveApi(Long aid, LocationRequest locationRequest);

    void updateResponseExample(EditAPIResponseExample editAPIResponseExample);

    void updateResponseParams(EditAPIResponseParams editAPIResponseParams);
}
