package io.whileaway.apit.account.repository;

import io.whileaway.apit.account.entity.Developer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long>, JpaSpecificationExecutor<Developer> {

    Optional<Developer> findByDeveloperName(String developerName);

    Optional<Developer> findByEmail(String email);

    Optional<List<Developer>> findByEmailOrDeveloperName(String email, String developerName);

    Optional<List<Developer>> findByEmailLikeOrDeveloperNameLike(String name, String email, Pageable pageable);
}
