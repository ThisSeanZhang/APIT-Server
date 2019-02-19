package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        API data = apiRepository.save(updateApi);
        return ResultUtil.success(ControllerEnum.SUCCESS, data);
    }

    @Override
    public API getById(Long aid) {
        return apiRepository.findByAid(aid).orElseThrow(() -> new CommonException(ControllerEnum.NOT_FOUND));
    }

    @Override
    public void inspectPermission(HttpServletRequest request, Long aid) {
        API api = getById(aid);
//        projectService.inspectPermission(request, api.getBelongProject());
    }

    @Override
    public void checkProjectOvert(Long id) {
//        projectService.checkOvert(getById(id).getBelongProject());
    }
}
