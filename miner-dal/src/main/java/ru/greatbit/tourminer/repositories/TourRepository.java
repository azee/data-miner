package ru.greatbit.tourminer.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.greatbit.tourminer.beans.Tour;
import ru.greatbit.tourminer.repositories.custom.TourRepositoryCustom;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */
public interface TourRepository extends PagingAndSortingRepository<Tour, String>, TourRepositoryCustom{
}
