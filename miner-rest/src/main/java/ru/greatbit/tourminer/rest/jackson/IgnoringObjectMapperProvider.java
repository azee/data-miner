package ru.greatbit.tourminer.rest.jackson;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created with IntelliJ IDEA.
 * Date: 2/8/13
 * Time: 11:57 AM
 */
@Provider
public class IgnoringObjectMapperProvider implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> type) {

        ObjectMapper result = new ObjectMapper();
        result.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        result.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, false);
        result.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, false);
        return result;
    }
}
