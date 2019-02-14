package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.repository.FolderRepository;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.api.specs.FolderSpec;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                .findInDB(folderRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<List<Node>> firstLayerFolders(Long belongProject, Long folderOwnerId) {
        if (Objects.isNull(belongProject) || Objects.isNull(folderOwnerId) ) throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        return new Spec<Folder, Node>()
                .appendCondition(FolderSpec.belongProject(()-> belongProject))
                .appendCondition(FolderSpec.folderOwnerId(()->folderOwnerId))
                .appendCondition(FolderSpec.folderParentIsNull())
                .findInDB(folderRepository::findAll)
                .convert(Node::new);
    }

    @Override
    public Result<List<Node>> folderContent(FilterFolder filterFolder) {
        List<Node> nodes = new ArrayList<>();
        List<Node> listResult = new ArrayList<>();
        List<Node> byBelongFolder = new ArrayList<>();

        try{ listResult = filterFolders(filterFolder).getData(); }
        catch (CommonException e) { System.out.println(e); }
        finally { if ( !listResult.isEmpty() ) nodes.addAll(listResult); }

        try{ byBelongFolder = apiService.findByBelongFolder(filterFolder.getParentId()).getData(); }
        catch (CommonException e) { System.out.println(e); }
        finally { if ( !byBelongFolder.isEmpty() ) nodes.addAll(byBelongFolder); }

        if (nodes.isEmpty())
            throw new CommonException(ControllerEnum.NOT_FOUND);
        return ResultUtil.success(ControllerEnum.SUCCESS, nodes);
    }
}
