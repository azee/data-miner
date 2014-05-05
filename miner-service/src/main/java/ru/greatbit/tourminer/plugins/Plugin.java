package ru.greatbit.tourminer.plugins;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.MethodNotSupportedException;
import ru.greatbit.tourminer.beans.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by azee on 5/5/14.
 */
public abstract class Plugin {

    public ClientResponse fetch(Source source) throws MethodNotSupportedException {
        //Build web-resource
        Client client = Client.create();
        WebResource webResource = client.resource(source.getUrl());

        //Add headers
        for (Header header : source.getHeaders()){
            webResource.header(header.getKey(), header.getValue());
        }

        //Add properties
        for (Property property : source.getProperties()){
            webResource.getProperties().put(property.getKey(), property.getValue());
        }

        //Evaluate request
        if (Method.GET.equals(source.getMethod())){
            return webResource.get(ClientResponse.class);
        }
        if (Method.POST.equals(source.getMethod())){
            return webResource.post(ClientResponse.class);
        }
        if (Method.PUT.equals(source.getMethod())){
            return webResource.put(ClientResponse.class);
        }

        throw new MethodNotSupportedException(source.getMethod());
    }

    public abstract List<Tour> parse(ClientResponse response);

    public void matchCountries(Source source, List<Tour> tours){
        Map<String, String> countryMap = new HashMap<>();
        for (CountryDict countryDict : source.getCountryDicts()){
            countryMap.put(countryDict.getOrigin(), countryDict.getCountryId());
        }

        for (Tour tour : tours){
            tour.setCountry(countryMap.get(tour.getCountry()));
        }
    }


}
