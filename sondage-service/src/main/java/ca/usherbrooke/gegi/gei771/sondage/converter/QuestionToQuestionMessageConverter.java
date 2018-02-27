package ca.usherbrooke.gegi.gei771.sondage.converter;

import ca.usherbrooke.gegi.gei771.messages.QuestionMessage;
import ca.usherbrooke.gegi.gei771.sondage.model.Question;
import org.springframework.core.convert.converter.Converter;

/**
 * @author beauchef on 2018-02-26.
 */
public class QuestionToQuestionMessageConverter implements Converter<Question, QuestionMessage> {

    @Override
    public QuestionMessage convert(Question question) {
        return QuestionMessage.builder()
            .id(question.getId())
            .text(question.getText())
            .build();
    }
}
