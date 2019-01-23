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

    public Developer() {
    }

    public Developer(String developerName, String developerPass) {
        this.developerName = developerName;
        this.developerPass = developerPass;
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
}
