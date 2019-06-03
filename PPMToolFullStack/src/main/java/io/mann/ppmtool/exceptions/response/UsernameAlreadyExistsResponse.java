package io.mann.ppmtool.exceptions.response;

/**
 * @Author: Mann
 * @Date: 2019-06-03 22:22
 */
public class UsernameAlreadyExistsResponse {

    private String username;

    public UsernameAlreadyExistsResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
