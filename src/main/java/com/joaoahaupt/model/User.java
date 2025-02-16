package com.joaoahaupt.model;

import lombok.Builder;
import lombok.Data;

@Data
public class User {

    public User() {
    }

    private Long id;
    private String username;


}
