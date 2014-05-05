package ru.greatbit.tourminer.rest.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.greatbit.tourminer.beans.Source;
import ru.greatbit.tourminer.beans.Tour;
import ru.greatbit.tourminer.plugins.Plugin;
import ru.greatbit.tourminer.plugins.PluginFactory;
import ru.greatbit.tourminer.service.services.SourceService;
import ru.greatbit.tourminer.service.services.TourService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 2/6/13
 * Time: 5:52 PM
 */

@Component
public class ScheduledFetcher {

    @Autowired
    SourceService sourceService;

    @Autowired
    TourService tourService;

    @Autowired
    PluginFactory pluginFactory;

    private final static Logger log = Logger.getLogger(ScheduledFetcher.class);

    @Scheduled(fixedRate = 7200000)
    public void executeInternal() throws Exception {
        for(Source source : sourceService.findAll()){
            Plugin plugin = pluginFactory.getPlugin(source);
            List<Tour> tours = plugin.parse(plugin.fetch(source));
            plugin.matchCountries(source, tours);
            tourService.updateBySource(source.getId(), tours);
        }
    }
}
