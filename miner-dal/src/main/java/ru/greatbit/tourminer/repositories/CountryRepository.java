package ru.greatbit.tourminer.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.greatbit.tourminer.beans.Country;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */
public interface CountryRepository extends PagingAndSortingRepository<Country, String>{
}
