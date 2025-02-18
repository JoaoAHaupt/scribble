package com.joaoahaupt.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Annotation {

    private Long id;
    private String title;
    private String description;
    private Date created;
    private Date updated;
    private String author;
    private String tag;
    private String link;
    private Long folderId;

}
