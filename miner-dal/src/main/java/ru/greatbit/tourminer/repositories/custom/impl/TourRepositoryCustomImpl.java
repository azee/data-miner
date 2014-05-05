package ru.greatbit.tourminer.repositories.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.greatbit.tourminer.beans.Tour;
import ru.greatbit.tourminer.repositories.custom.TourRepositoryCustom;

import java.util.Arrays;
import java.util.List;

/**
 * Created by azee on 5/5/14.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TourRepositoryCustomImpl implements TourRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Tour> findSortedByPrice(int limit) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, Arrays.asList("absPrice")));
        if(limit > 0){
            query.limit(limit);
        }
        return mongoOperations.find(query, Tour.class);
    }

    @Override
    public List<Tour> findBySource(String source) {
        Query query = new Query();
        query.addCriteria(Criteria.where("source").is(source));
        return mongoOperations.find(query, Tour.class);
    }

    @Override
    public void updateBySource(String source, List<Tour> tours) {
        Query query = new Query();
        query.addCriteria(Criteria.where("source").is(source));
        mongoOperations.remove(query, Tour.class);
        mongoOperations.save(tours);
    }
}
