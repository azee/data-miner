package ru.greatbit.tourminer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.greatbit.tourminer.beans.Tour;
import ru.greatbit.tourminer.service.services.TourService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by azee on 5/5/14.
 */
@Component
@Path("/tours")
public class TourRest {

    @Autowired
    TourService tourService;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public List<Tour> getHotTours(@QueryParam("limit") int limit){
        return tourService.findSortedByPrice(limit);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public List<Tour> getHotTours(String country, @QueryParam("limit") int limit){
        return tourService.findSortedByPrice(country, limit);
    }
}
