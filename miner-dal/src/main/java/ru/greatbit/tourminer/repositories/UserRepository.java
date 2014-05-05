package ru.greatbit.tourminer.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.greatbit.tourminer.beans.User;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */
public interface UserRepository extends PagingAndSortingRepository<User, String>{

    @Query("{ 'token' : ?0 }")
    User findByToken(String token);

    @Query("{ 'sid' : ?0 }")
    User findByCookie(String sid);

    @Query ("{ 'name' : ?0 }")
    User findByName(String name);

    @Query ("{ 'email' : ?0 }")
    User findByEmail(String email);
}
