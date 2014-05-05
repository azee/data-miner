package ru.greatbit.tourminer.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.greatbit.tourminer.beans.Country;
import ru.greatbit.tourminer.beans.Source;
import ru.greatbit.tourminer.repositories.CountryRepository;
import ru.greatbit.tourminer.repositories.SourceRepository;

import java.util.List;

/**
 * Created by azee on 5/5/14.
 */
@Service
public class SourceService {

    @Autowired
    SourceRepository sourceRepository;

    /**
     * Get all sources
     * @return
     */
    public List<Source> findAll(){
        return (List<Source>) sourceRepository.findAll();
    }

    /**
     * Find a source by id
     * @param id
     * @return
     */
    public Source getSource(String id){
        return sourceRepository.findOne(id);
    }

    /**
     * Update a source
     * @param source
     * @return
     */
    public Source updateSource(Source source){
        return sourceRepository.save(source);
    }

    /**
     * Delete a source
     * @param id
     */
    public void removeSource(String id){
        sourceRepository.delete(id);
    }

    /**
     * Find sources by group id
     * @param groupId
     * @return
     */
    public List<Source> getSourcesByGroup(String groupId){
        return sourceRepository.findByGroupId(groupId);
    }



}
