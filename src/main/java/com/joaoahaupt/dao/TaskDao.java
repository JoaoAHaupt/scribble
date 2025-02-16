package com.joaoahaupt.dao;

import com.joaoahaupt.model.Task;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Task.class)
public interface TaskDao {


}
