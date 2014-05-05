package ru.greatbit.tourminer.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.greatbit.tourminer.beans.Tour;
import ru.greatbit.tourminer.repositories.TourRepository;

import java.util.List;

/**
 * Created by azee on 5/5/14.
 */
@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    /**
     * Find data sorted by price
     * @param limit
     * @return
     */
    public List<Tour> findSortedByPrice(int limit){
        return tourRepository.findSortedByPrice(limit);
    }

    /**
     * Find sorted by price with specific country
     * @param country
     * @param limit
     * @return
     */
    public List<Tour> findSortedByPrice(String country, int limit){
        return tourRepository.findSortedByPrice(country, limit);
    }


    /**
     * Find data by source
     * @param source
     * @return
     */
    public List<Tour> findBySource(String source){
        return tourRepository.findBySource(source);
    }


    /**
     * Update data for entire source
     * @param source
     * @param tours
     */
    public void updateBySource(String source, List<Tour> tours){
        updateBySource(source, tours);
    }


}
