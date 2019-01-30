package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.repository.FolderRepository;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Override
    public Result<List<Node>> getFoldersNodeByProjectId(Long pid) {
        Optional<List<Folder>> primitive = folderRepository.findByBelongProject(pid);
        if ( primitive.isEmpty() || primitive.get().isEmpty())
            throw new CommonException(ControllerEnum.NOT_FOUND);
        return ResultUtil.success(
                ControllerEnum.SUCCESS,
                primitive.get().stream()
                        .map(Node::new)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Result<List<Node>> filterFolders(FilterFolder filterFolder) {
        return null;
    }

    @Override
    public Result<List<Node>> firstLayerFolders(Long belongProject, Long folderOwnerId) {
        Optional<List<Folder>> primitive = folderRepository
                .findByBelongProjectAndFolderOwnerId(belongProject, folderOwnerId);
        if (primitive.isEmpty() || primitive.get().isEmpty())
            throw new CommonException(ControllerEnum.NOT_FOUND);
        return ResultUtil.success(
                ControllerEnum.SUCCESS,
                primitive.get().stream()
                .map(Node::new)
                .collect(Collectors.toList())
        );
    }
}
