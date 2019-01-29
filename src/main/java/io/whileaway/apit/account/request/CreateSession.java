package io.whileaway.apit.account.request;

public class CreateSession {

    private String developerNameOrEmail;
    private String  developerPass;
    private Boolean isRememberMe;

    public String getDeveloperNameOrEmail() {
        return developerNameOrEmail;
    }

    public void setDeveloperNameOrEmail(String developerNameOrEmail) {
        this.developerNameOrEmail = developerNameOrEmail;
    }

    public String getDeveloperPass() {
        return developerPass;
    }

    public void setDeveloperPass(String developerPass) {
        this.developerPass = developerPass;
    }

    public Boolean getRememberMe() {
        return isRememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        isRememberMe = rememberMe;
    }
}
