package io.whileaway.apit.account.request;

import io.whileaway.apit.account.entity.Developer;

import javax.validation.constraints.NotNull;

public class CreateDeveloper {

    @NotNull(message = "")
    private String developerName;
    @NotNull(message = "")
    private String developerPass;
    @NotNull(message = "")
    private String email;

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

    public Developer convertToDeveloper() {
        return new Developer(this.developerName, this.developerPass, this.email);
    }
}
