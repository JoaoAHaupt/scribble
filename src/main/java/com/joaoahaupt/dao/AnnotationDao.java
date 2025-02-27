package com.joaoahaupt.dao;

import com.joaoahaupt.model.Annotation;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Date;
import java.util.List;


@RegisterBeanMapper(Annotation.class)
public interface AnnotationDao {

    @SqlUpdate("INSERT INTO annotations (title, description, created, updated, author, tag, link, folder_id) " +
            "VALUES (:title, :description, :created, :updated, :author, :tag, :link, :folder_id)")
    void insertAnnotation
            (
                    @Bind("title") String title,
                    @Bind("description") String description,
                    @Bind("created") Date created,
                    @Bind("updated") Date updated,
                    @Bind("author") String author,
                    @Bind("tag") String tag,
                    @Bind("link") String link,
                    @Bind("folder_id") Long folderId
    );

    @SqlQuery("SELECT *  FROM annotations WHERE folder_id = :folder_id")
    List<Annotation> selectAnnotationFolder(@Bind("folder_id") Long folderId);
}
