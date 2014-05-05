package ru.greatbit.tourminer.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.greatbit.tourminer.beans.Source;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */
public interface SourceRepository extends PagingAndSortingRepository<Source, String>{
    @Query("{ 'groupId' : ?0 }")
    List<Source> findByGroupId(String groupId);
}

