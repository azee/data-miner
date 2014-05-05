package ru.greatbit.tourminer.repositories.custom;

import ru.greatbit.tourminer.beans.Tour;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */
public interface TourRepositoryCustom {
    public List<Tour> findSortedByPrice(int limit);
    public List<Tour> findSortedByPrice(String country, int limit);
    public List<Tour> findBySource(String source);
    public void updateBySource(String source, List<Tour> tours);

}
