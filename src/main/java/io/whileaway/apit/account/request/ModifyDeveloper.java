package io.whileaway.apit.account.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModifyDeveloper {

    private Long developerId;
    @NotNull(message = "状态不能为空")
    private Integer status;
    @NotNull(message = "管理员状态不能为空")
    private Boolean admin;
    private String developerPass;
    @NotNull(message = "require email")
    @Length(min = 1 ,max = 20)
    @NotBlank(message = "email不能为空")
    private String email;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
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

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }
}
