package ru.greatbit.tourminer.plugins;

import org.springframework.stereotype.Service;
import ru.greatbit.tourminer.beans.Source;

/**
 * Created by azee on 5/5/14.
 */
@Service
public class PluginFactory {
    public Plugin getPlugin(Source source) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<? extends Plugin> plugin = (Class<? extends Plugin>) Class.forName(source.getPluginId());
        return plugin.newInstance();
    }
}
