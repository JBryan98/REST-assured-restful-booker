package objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class authUserPayload {
    @JsonProperty(value="username") private String username;
    @JsonProperty(value="password") private String password;

    public authUserPayload(String username, String password){
        this.username = username;
        this.password = password;
    }
}
