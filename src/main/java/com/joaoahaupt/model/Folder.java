package com.joaoahaupt.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Folder {

    public Folder() {}

    private Long id;
    private String name;
    private String description;

}
