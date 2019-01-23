package io.whileaway.apit.account.service.impl;

import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.repository.DeveloperRepository;
import io.whileaway.apit.account.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public void createDeveloper(Developer developer) {
        developerRepository.save(developer);
    }

    @Override
    public Developer findByName(String developerName) {
        Optional<Developer> developer = developerRepository.findByDeveloperName(developerName);
        return developer.get();
    }
}
