package com.chzu.ice.chat.pojo.gson.req;
import javax.validation.constraints.NotNull;

/**
 * @author mason
 */
public class LoginReq {
    @NotNull(message = "登录名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "publicKey不能为空")
    private String publicKey;

    public LoginReq(@NotNull(message = "登录名不能为空") String username, @NotNull(message = "密码不能为空") String password, @NotNull(message = "publicKey不能为空") String publicKey) {
        this.username = username;
        this.password = password;
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

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
