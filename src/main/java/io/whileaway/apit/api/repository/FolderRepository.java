package io.whileaway.apit.api.repository;

import io.whileaway.apit.api.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> , JpaSpecificationExecutor<Folder> {

    Optional<List<Folder>> findByBelongProject(Long belongProject);

    Optional<List<Folder>> findByParentId(Long parentId);

    Optional<List<Folder>> findByBelongProjectAndFolderOwnerId(Long belongProject, Long folderOwnerId);

    Optional<Folder> findByFidAndStatus(Long fid, Integer code);
}
