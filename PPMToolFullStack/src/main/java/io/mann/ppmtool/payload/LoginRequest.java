package io.mann.ppmtool.payload;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Mann
 * @Date: 2019-06-03 23:12
 */
public class LoginRequest {

    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

