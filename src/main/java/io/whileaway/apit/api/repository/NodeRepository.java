package io.whileaway.apit.api.repository;

import io.whileaway.apit.api.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface NodeRepository extends JpaRepository<Node, Long> , JpaSpecificationExecutor<Node> {

    /**
     * obtain first layer Node By Belong Project And OwnerId And ParentId is Null
     * @return Optional<List<Node>>
     */
    Optional<List<Node>> findByBelongProjectAndOwnerIdAndParentIdNull(Long belongProject, Long ownerId);

    /**
     * obtain Node content By parentId
     * @return Optional<List<Node>>
     */
    Optional<List<Node>> findByParentId(Long parentId);
}
