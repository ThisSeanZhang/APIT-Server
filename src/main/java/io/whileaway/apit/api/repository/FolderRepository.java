package io.whileaway.apit.api.repository;

import io.whileaway.apit.api.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    Optional<List<Folder>> findByBelongProject(Long belongProject);

    Optional<List<Folder>> findByParentId(Long parentId);

    Optional<List<Folder>> findByBelongProjectAndFolderOwnerId(Long belongProject, Long folderOwnerId);
}
