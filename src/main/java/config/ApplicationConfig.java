package config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(chefes.ChefeService.class);
        resources.add(config.ConstraintViolationExceptionMapper.class);
        resources.add(config.CrossOriginFilter.class);
        resources.add(restaurantes.RestauranteService.class);
        resources.add(tipos_cozinha.TipoCozinhaService.class);
    }

}
