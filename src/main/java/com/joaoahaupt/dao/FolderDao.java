package com.joaoahaupt.dao;

import com.joaoahaupt.model.Folder;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(Folder.class)
public interface FolderDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS folders (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), description TEXT, user_id BIGINT, FOREIGN KEY (user_id) REFERENCES users(id))")
    void createTable();

    @SqlUpdate("INSERT INTO folders (name, description, user_id) VALUES (:name, :description, :user_id)")
    void insertFolder(@Bind("name") String name, @Bind("description") String description, @Bind("user_id") Long userId);

    @SqlQuery("SELECT * FROM folders WHERE user_id = :id")
    List<Folder> selectUserFolders(@Bind("id") Long id);
}
