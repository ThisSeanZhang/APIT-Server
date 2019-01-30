package io.whileaway.apit.api.repository;

import io.whileaway.apit.api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<List<Project>> findByProjectOwner(Long projectOwner);

}
