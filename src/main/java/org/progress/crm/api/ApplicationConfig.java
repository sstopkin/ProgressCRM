package org.progress.crm.api;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.reflections.Reflections;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Reflections reflections = new Reflections(ApplicationConfig.class.getPackage().getName());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(javax.ws.rs.Path.class);
//        resources.add(UploadFileService.class);
        classes.add(MultiPartFeature.class);
        return classes;
    }

}
