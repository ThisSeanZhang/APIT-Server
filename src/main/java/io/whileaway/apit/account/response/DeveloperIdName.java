package io.whileaway.apit.account.response;

import io.whileaway.apit.account.entity.Developer;

public class DeveloperIdName {

    private Long id;
    private String name;

    public DeveloperIdName(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeveloperIdName(Developer developer) {
        this.id = developer.getDeveloperId();
        this.name = developer.getDeveloperName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
