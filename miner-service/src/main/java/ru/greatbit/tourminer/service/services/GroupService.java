package ru.greatbit.tourminer.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.greatbit.tourminer.beans.Group;
import ru.greatbit.tourminer.repositories.GroupRepository;
import ru.greatbit.tourminer.repositories.SourceRepository;

import java.util.List;

/**
 * Created by azee on 5/5/14.
 */
@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SourceRepository sourceRepository;

    /**
     * Get all groups
     * @return
     */
    public List<Group> findAll(){
        return (List<Group>) groupRepository.findAll();
    }

    /**
     * Find a group by id
     * @param id
     * @return
     */
    public Group getGroup(String id){
        return groupRepository.findOne(id);
    }

    /**
     * Update a group
     * @param group
     * @return
     */
    public Group updateGroup(Group group){
        return groupRepository.save(group);
    }

    /**
     * Delete a group and connected sources
     * @param id
     */
    public void removeGroup(String id){
        groupRepository.delete(id);
        sourceRepository.delete(sourceRepository.findByGroupId(id));
    }



}
