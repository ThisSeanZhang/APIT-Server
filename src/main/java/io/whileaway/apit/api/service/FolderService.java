package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import java.util.List;

public interface FolderService {

    Result<Folder> createFolder(Folder folder);

    Result<List<Node>> getFoldersNodeByProjectId (Long pid);

    Result<List<Node>> filterFolders(FilterFolder filterFolder);

    Result<List<Node>> firstLayerFolders(Long belongProject);

    Result<List<Node>> folderContent(FilterFolder filterFolder);

}
