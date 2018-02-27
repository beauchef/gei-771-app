package ca.usherbrooke.gegi.gei771.sondage.controller;

import ca.usherbrooke.gegi.gei771.sondage.exception.UsagerNotFoundException;
import ca.usherbrooke.gegi.gei771.sondage.repository.ReponseRepository;
import ca.usherbrooke.gegi.gei771.sondage.repository.SondageRepository;
import ca.usherbrooke.gegi.gei771.sondage.repository.UsagerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author beauchef on 2018-02-25.
 */
@RunWith(MockitoJUnitRunner.class)
public class SondageControllerTest {

    @Mock
    private ConversionService conversionService;
    @Mock
    private UsagerRepository usagerRepository;
    @Mock
    private SondageRepository sondageRepository;
    @Mock
    private ReponseRepository reponseRepository;
    @InjectMocks
    private SondageController sondageController;

    @Test(expected = UsagerNotFoundException.class)
    public void givenNoUserMatchesUuid_whenListSondages_thenShouldGetException() throws Exception {
        when(usagerRepository.findOne(any(UUID.class))).thenReturn(null);
        sondageController.list(UUID.randomUUID());
    }
}
