package io.whileaway.apit.api.repository;

import io.whileaway.apit.api.entity.API;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface APIRepository extends JpaRepository<API, Long> , JpaSpecificationExecutor<API> {

    Optional<List<API>> findByBelongProject(Long belongProject);

    Optional<List<API>> findByBelongFolder(Long belongNode);

    Optional<API> findByAidAndStatus(Long aid, Integer status);
}
