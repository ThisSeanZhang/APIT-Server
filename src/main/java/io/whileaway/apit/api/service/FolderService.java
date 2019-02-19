package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Folder;
import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.api.request.FilterFolder;
import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.BiFunction;

public interface FolderService {

    Result<Folder> createFolder(Folder folder);

    Result<List<Node>> getFoldersNodeByProjectId (Long pid);

    Result<List<Node>> filterFolders(FilterFolder filterFolder);

    Result<List<Node>> firstLayerFolders(Long belongProject);

    Result<List<Node>> folderContent(FilterFolder filterFolder);

    void inspectPermission(HttpServletRequest request, Long folderId, BiFunction<Project, Long, Boolean> check);

    void checkProjectOvert(Long id);
}
