package com.khayal.turboaz.dto;

import com.khayal.turboaz.model.Car;
import com.khayal.turboaz.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String email;

    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
