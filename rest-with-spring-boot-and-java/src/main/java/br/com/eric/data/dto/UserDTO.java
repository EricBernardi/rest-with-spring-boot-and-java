package br.com.eric.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(getUsername(), userDTO.getUsername()) && Objects.equals(getPassword(), userDTO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
