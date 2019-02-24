package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.enums.StatusDict;
import io.whileaway.apit.api.repository.APIRepository;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.specs.APISpec;
import io.whileaway.apit.base.CommonException;
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
//    private final ProjectService projectService;

    @Autowired
    public APIServiceImpl(APIRepository apiRepository) {
        this.apiRepository = apiRepository;
//        this.projectService = projectService;
    }

    @Override
    public Result<List<Node>> findByBelongFolder(Long belongFolder) {
        return new Spec<API, Node> ()
                .appendCondition(APISpec.belongFolder(()->belongFolder))
                .appendCondition(APISpec.statusNormal())
                .findInDB(apiRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<List<Node>> findFirstLayerByProjectId(Long belongProject) {
        return new Spec<API, Node> ()
                .appendCondition(APISpec.belongProject(()->belongProject))
                .appendCondition(APISpec.belongFolderIsNull())
                .appendCondition(APISpec.statusNormal())
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
        return ResultUtil.success(ControllerEnum.SUCCESS, data);
    }

    @Override
    public Result<API> updateApi(API updateApi) {
        updateApi.setStatus(StatusDict.NORMAL.getCode());
        API data = apiRepository.save(updateApi);
        return ResultUtil.success(ControllerEnum.SUCCESS, data);
    }

    @Override
    public API getById(Long aid) {
        return apiRepository.findByAid(aid).orElseThrow(() -> new CommonException(ControllerEnum.NOT_FOUND));
    }

    @Override
    public Result<API> delApi(Long aid) {
        API api = getById(aid);
        api.setStatus(StatusDict.DELETE.getCode());
        apiRepository.save(api);
        return ResultUtil.success();
    }

}
