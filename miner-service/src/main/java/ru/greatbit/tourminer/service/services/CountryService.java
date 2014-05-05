package ru.greatbit.tourminer.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.greatbit.tourminer.beans.Country;
import ru.greatbit.tourminer.beans.Tour;
import ru.greatbit.tourminer.repositories.CountryRepository;
import ru.greatbit.tourminer.repositories.TourRepository;

import java.util.List;

/**
 * Created by azee on 5/5/14.
 */
@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    /**
     * Get all countries
     * @return
     */
    public List<Country> findAll(){
        return (List<Country>) countryRepository.findAll();
    }

    /**
     * Find a country by id
     * @param id
     * @return
     */
    public Country getCountry(String id){
        return countryRepository.findOne(id);
    }

    /**
     * Update a country
     * @param country
     * @return
     */
    public Country updateCountry(Country country){
        return countryRepository.save(country);
    }

    /**
     * Delete a country
     * @param id
     */
    public void removeCountry(String id){
        countryRepository.delete(id);
    }



}
