package ca.usherbrooke.gegi.gei771.sondage.converter;

import ca.usherbrooke.gegi.gei771.messages.QuestionMessage;
import ca.usherbrooke.gegi.gei771.messages.ReponseMessage;
import ca.usherbrooke.gegi.gei771.sondage.model.Reponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

/**
 * @author beauchef on 2018-02-26.
 */
public class ReponseToReponseMessageConverter implements Converter<Reponse, ReponseMessage> {

    private final ConversionService conversionService;

    public ReponseToReponseMessageConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ReponseMessage convert(Reponse reponse) {
        return ReponseMessage.builder()
            .id(reponse.getId())
            .text(reponse.getText())
            .question(conversionService.convert(reponse.getQuestion(), QuestionMessage.class))
            .build();
    }
}
