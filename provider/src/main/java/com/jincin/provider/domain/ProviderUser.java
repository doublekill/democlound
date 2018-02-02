package com.jincin.provider.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;

/**
 * Created by Mr_Li on 2017/7/11.
 */
@ApiModel(value = "用户")
@Entity
@Table(name="provider_user")
public class ProviderUser {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "用户Id")
    private int Id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "住址")
    private String adress;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}