package ca.usherbrooke.gegi.gei771.sondage.converter;

import ca.usherbrooke.gegi.gei771.messages.QuestionMessage;
import ca.usherbrooke.gegi.gei771.messages.SondageMessage;
import ca.usherbrooke.gegi.gei771.sondage.model.Sondage;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beauchef on 2018-02-26.
 */
public class SondageToSondageMessageConverter implements Converter<Sondage, SondageMessage> {

    private final ConversionService conversionService;

    public SondageToSondageMessageConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public SondageMessage convert(Sondage sondage) {
        List<QuestionMessage> questions = sondage.getQuestions().stream()
            .map(q -> conversionService.convert(q, QuestionMessage.class))
            .collect(Collectors.toList());
        return SondageMessage.builder()
            .id(sondage.getId())
            .description(sondage.getDescription())
            .questions(questions)
            .build();
    }
}
