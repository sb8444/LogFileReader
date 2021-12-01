package com.filereader.logfilereader.repository;

import com.filereader.logfilereader.model.LogDbModel;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<LogDbModel, String> {
}
