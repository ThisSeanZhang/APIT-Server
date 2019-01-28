package io.whileaway.apit.account.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.whileaway.apit.account.entity.Developer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateDeveloper {

    @NotNull(message = "")
    @NotBlank(message = "email不能为空")
    private String developerName;
    @NotNull(message = "")
    @NotBlank(message = "email不能为空")
    private String developerPass;
    @NotNull(message = "")
    @NotBlank(message = "email不能为空")
    private String email;

//    @NotNull
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private Date joinTime;

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

//    public Date getJoinTime() {
//        return joinTime;
//    }
//
//    public void setJoinTime(Date joinTime) {
//        this.joinTime = joinTime;
//    }
}
