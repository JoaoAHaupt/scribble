package com.joaoahaupt.dao;

import com.joaoahaupt.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(User.class)
public interface UserDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY, name VARCHAR(255))")
    void createTable();

    @SqlUpdate("INSERT INTO users (id, name) VALUES (:id, :name)")
    void insertUser(@Bind("id") Long id, @Bind("name") String name);

    @SqlQuery("SELECT * FROM users WHERE id = :id")
    User getUserById(@Bind("id") Long id);
}
