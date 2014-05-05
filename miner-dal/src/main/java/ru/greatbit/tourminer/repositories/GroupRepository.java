package ru.greatbit.tourminer.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.greatbit.tourminer.beans.Group;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */
public interface GroupRepository extends PagingAndSortingRepository<Group, String>{
}
