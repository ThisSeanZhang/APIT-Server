package io.whileaway.apit.api.repository;

import io.whileaway.apit.api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> , JpaSpecificationExecutor<Project> {

    Optional<List<Project>> findByProjectOwner(Long projectOwner);

    @Query("select p from Project p " +
            "where p.pid = :pid " +
            "and p.whoJoins like CONCAT(:developerId,',%')" +
            "or  p.whoJoins like CONCAT('%,',:developerId,',%')" +
            "or  p.whoJoins like CONCAT('%,',:developerId)")
    Optional<Project> checkDeveloperIsJoin (@Param("pid") Long pid, @Param("developerId") Long developerId);

    @Query("select p from Project p " +
            "where p.projectOwner = :developerId " +
            "or p.whoJoins like CONCAT(:developerId,',%')" +
            "or  p.whoJoins like CONCAT('%,',:developerId,',%')" +
            "or  p.whoJoins like CONCAT('%,',:developerId)")
    Optional<List<Project>> checkAllObtainProject (@Param("developerId") Long developerId);
}
