package ru.greatbit.tourminer.repositories.custom;

import ru.greatbit.tourminer.beans.Tour;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */
public interface TourRepositoryCustom {
    List<Tour> findSortedByPrice(int limit);
    List<Tour> findBySource(String source);
    void updateBySource(String source, List<Tour> tours);

}
