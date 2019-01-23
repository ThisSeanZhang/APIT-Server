package io.whileaway.apit.account.repository;

import io.whileaway.apit.account.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Optional<Developer> findByDeveloperName(String developerName);
}
