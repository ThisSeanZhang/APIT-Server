package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import java.util.List;
import java.util.Optional;

public interface FolderService {

    Folder createFolder(Folder folder);

    List<Node> filterFolders(FilterFolder filterFolder);

    List<Node> firstLayer(Long belongProject);

    List<Node> folderContent(FilterFolder filterFolder);

    Result<List<Node>> subFolders(FilterFolder filterFolder);

    Result<Folder> findFolderById(Long fid);

    Result<Folder> modifyFolder(Folder convertToFolder);

    Result<Folder> deleteFolder(Long fid);

    Folder getFolder(Long id);

    Optional<Folder> getFolderUncheck(Long id);
}
