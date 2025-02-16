package com.joaoahaupt.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class Task {

    public Task() {}

    private Long id;
    private String name;
    private String description;
    private String link;
    private Date limit;
    private Long folderId;


}
