package io.whileaway.apit.account.entity;

import javax.persistence.*;

@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long developerId;
    @Column(length = 50,unique = true, nullable = false)
    private String developerName;
    @Column(length = 16, nullable = false)
    private String developerPass;
    @Column(length = 50, nullable = false)
    private String email;
    private Long defaultFolder;
    private Long defaultProject;

    public Developer() {
    }

    public Developer(String developerName, String developerPass, String email) {
        this.developerName = developerName;
        this.developerPass = developerPass;
        this.email = email;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperPass() {
        return developerPass;
    }

    public void setDeveloperPass(String developerPass) {
        this.developerPass = developerPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDefaultFolder() {
        return defaultFolder;
    }

    public void setDefaultFolder(Long defaultFolder) {
        this.defaultFolder = defaultFolder;
    }

    public Long getDefaultProject() {
        return defaultProject;
    }

    public void setDefaultProject(Long defaultProject) {
        this.defaultProject = defaultProject;
    }
}
