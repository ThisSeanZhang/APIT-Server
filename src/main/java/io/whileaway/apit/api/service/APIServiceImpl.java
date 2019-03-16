package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.enums.StatusDict;
import io.whileaway.apit.api.repository.APIRepository;
import io.whileaway.apit.api.request.LocationRequest;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.specs.APISpec;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class APIServiceImpl implements APIService {

    private final APIRepository apiRepository;

    @Autowired
    public APIServiceImpl(APIRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public List<Node> findByBelongFolder(Long belongFolder) {
        return new Spec<API, Node> ()
                .appendCondition(APISpec.belongFolder(()->belongFolder))
                .appendCondition(APISpec.statusNormal())
                .findInDBUnCheck(apiRepository::findAll)
                .convertOther(Node::new);
    }

    @Override
    public List<Node> findFirstLayer(Long belongProject) {
        return new Spec<API, Node> ()
                .appendCondition(APISpec.belongProject(()->belongProject))
                .appendCondition(APISpec.belongFolderIsNull())
                .appendCondition(APISpec.statusNormal())
                .findInDBUnCheck(apiRepository::findAll)
                .convertOther(Node::new);
    }

    @Override
    public API findById(Long aid) {
        if (Objects.isNull(aid)) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return getApi(aid);
    }

    @Override
    public API createAPI(API api) {
        return apiRepository.save(api);
    }

    @Override
    public API updateApi(API updateApi) {
        updateApi.setStatus(StatusDict.NORMAL.getCode());
        return apiRepository.save(updateApi);
    }

    @Override
    public List<API> getByBelongFolder(Long fid) {
        return new Spec<API, API> ()
                .appendCondition(APISpec.belongFolder(()->fid))
                .appendCondition(APISpec.statusNormal())
                .findInDBUnCheck(apiRepository::findAll)
                .doNothing().getData();
    }

    @Override
    public void saveAll(List<API> apis) {
        apiRepository.saveAll(apis);
    }

    @Override
    public API getApi(Long aid) {
        return getApiUnCheck(aid)
                .orElseThrow(() -> new CommonException(ControllerEnum.NOT_FOUND));
    }

    public Optional<API> getApiUnCheck(Long aid) {
        return apiRepository.findByAidAndStatus(aid, StatusDict.NORMAL.getCode());
    }

    @Override
    public void delApi(Long aid) {
        API api = getApi(aid);
        api.setStatus(StatusDict.DELETE.getCode());
        apiRepository.save(api);
    }

    @Override
    public void moveApi(Long aid, LocationRequest locationRequest) {
        API api = getApi(aid);
        api.setBelongFolder(locationRequest.getBelongFolder());
        api.setBelongProject(locationRequest.getBelongProject());
        apiRepository.save(api);
    }

}
