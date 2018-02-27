package ca.usherbrooke.gegi.gei771.sondage.configuration;

import ca.usherbrooke.gegi.gei771.sondage.converter.QuestionToQuestionMessageConverter;
import ca.usherbrooke.gegi.gei771.sondage.converter.ReponseToReponseMessageConverter;
import ca.usherbrooke.gegi.gei771.sondage.converter.SondageToSondageMessageConverter;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author beauchef on 2018-02-26.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public UrlPathHelper urlPathHelper() {
        return new UrlPathHelper();
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        return new ConversionServiceFactoryBean() {
            @Override
            public void afterPropertiesSet() {
                super.afterPropertiesSet();
                val conversionService = getObject();
                val registry = (ConverterRegistry)conversionService;
                registry.addConverter(new QuestionToQuestionMessageConverter());
                registry.addConverter(new ReponseToReponseMessageConverter(conversionService));
                registry.addConverter(new SondageToSondageMessageConverter(conversionService));
            }
        };
    }
}
