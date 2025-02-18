package com.SpringBoot.FoundIt_BackEnd.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Login {

    @NotBlank(message = " UserName is Mandatory ......! ")
    @Size(min = 2,message = " UserName Must Be Between 2 & 30 Characters .......! ",max = 40)
    private String userName;

    @NotBlank(message = " Password is Mandatory ......! ")
    @Size(min = 8,message = " Too Weak .......! ")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

