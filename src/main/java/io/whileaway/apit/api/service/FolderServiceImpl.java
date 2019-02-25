package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.API;
import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.enums.StatusDict;
import io.whileaway.apit.api.repository.FolderRepository;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.specs.FolderSpec;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.base.enums.ResultEnum;
import io.whileaway.apit.utils.DataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final APIService apiService;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository, APIService apiService) {
        this.folderRepository = folderRepository;
        this.apiService = apiService;
    }

    @Override
    public Result<Folder> createFolder(Folder folder) {
        return ResultUtil.success(ControllerEnum.SUCCESS, folderRepository.save(folder));
    }

    @Override
    public Result<List<Node>> getFoldersNodeByProjectId(Long pid) {
        if (Objects.isNull(pid)) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return new Spec<Folder, Node>()
                .appendCondition(FolderSpec.belongProject(()-> pid))
                .findInDB(folderRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<List<Node>> filterFolders(FilterFolder filterFolder) {
        System.out.println(filterFolder.toString());
        return new Spec<Folder, Node>()
                .appendCondition(FolderSpec.belongProject(filterFolder::getBelongProject))
                .appendCondition(FolderSpec.folderOwnerId(filterFolder::getFolderOwnerId))
                .appendCondition(FolderSpec.folderName(filterFolder::getFolderName))
                .appendCondition(FolderSpec.folderParentId(filterFolder::getParentId))
                .appendCondition(FolderSpec.statusNormal())
                .findInDB(folderRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<List<Node>> firstLayerFolders(Long belongProject) {
        if (Objects.isNull(belongProject) ) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return new Spec<Folder, Node>()
                .appendCondition(FolderSpec.belongProject(()-> belongProject))
                .appendCondition(FolderSpec.folderParentIsNull())
                .appendCondition(FolderSpec.statusNormal())
                .findInDB(folderRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<List<Node>> folderContent(FilterFolder filterFolder) {
        List<Node> nodes = new ArrayList<>();
        List<Node> listResult = new ArrayList<>();
        List<Node> byBelongFolder = new ArrayList<>();

        try{ listResult = filterFolders(filterFolder).getData(); }
        catch (CommonException e) { System.out.println(e.getMessage()); }
        finally { if ( !listResult.isEmpty() ) nodes.addAll(listResult); }

        try{ byBelongFolder = apiService.findByBelongFolder(filterFolder.getParentId()).getData(); }
        catch (CommonException e) { System.out.println(e.getMessage()); }
        finally { if ( !byBelongFolder.isEmpty() ) nodes.addAll(byBelongFolder); }

        if (nodes.isEmpty())
            throw new CommonException(ControllerEnum.NOT_FOUND);
        return ResultUtil.success(ControllerEnum.SUCCESS, nodes);
    }

    @Override
    public Result<List<Node>> subFolders(FilterFolder filterFolder) {
        return new Spec<Folder, Node>()
                .appendCondition(FolderSpec.folderParentId(filterFolder::getParentId))
                .appendCondition(FolderSpec.statusNormal())
                .findInDBUnCheck(folderRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<Folder> findFolderById(Long fid) {
        if (Objects.isNull(fid))
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return ResultUtil.success(
                folderRepository.findByFidAndStatus(fid, StatusDict.NORMAL.getCode())
                        .orElseThrow(()->new CommonException(ControllerEnum.NOT_FOUND)));
    }

    @Override
    @Transactional
    public Result<Folder> modifyFolder(Folder folder) {
        Folder data = getFolder(folder.getFid());
        data.setParentId(folder.getParentId());
        data.setBelongProject(folder.getBelongProject());
        data.setFolderName(folder.getFolderName());
        return ResultUtil.success(folderRepository.save(data));
    }

    @Override
    @Transactional
    public Result<Folder> deleteFolder(Long fid) {
        Folder folder = getFolder(fid);
        folder.setStatus(StatusDict.DELETE.getCode());
        Optional<List<Folder>> subFolder = folderRepository.findByParentId(fid);
        subFolder.ifPresent(folders -> folders.forEach(f -> deleteFolder(f.getFid())));
        List<API> subApi;
        try {
            subApi = apiService.getByBelongFolder(fid);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            subApi = new ArrayList<>();
        }
        if (!subApi.isEmpty()) {
            List<API> collects = subApi.stream().peek(sub -> sub.setStatus(StatusDict.DELETE.getCode()))
                    .collect(Collectors.toList());
            collects.forEach( a -> System.out.println(a.toString()));
            apiService.saveAll(collects);
        }
        return ResultUtil.success(folderRepository.save(folder));
    }

    private Folder getFolder(Long id) {
        return folderRepository.findById(id).orElseThrow(()->new CommonException(ControllerEnum.NOT_FOUND));
    }
}
