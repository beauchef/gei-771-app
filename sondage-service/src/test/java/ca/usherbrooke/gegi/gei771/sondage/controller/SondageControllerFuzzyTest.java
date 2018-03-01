package ca.usherbrooke.gegi.gei771.sondage.controller;

import com.redfin.fuzzy.Any;
import com.redfin.fuzzy.Generator;
import com.redfin.fuzzy.cases.StringCase;
import com.redfin.fuzzy.junit.FuzzyRule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author beauchef on 2018-02-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class SondageControllerFuzzyTest {

    @Rule
    public FuzzyRule fuzzyRule = FuzzyRule.REPORTING_ALL_FAILURES;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Ignore
    public void test() throws Exception {
        Generator<String> userId = Generator.of(new StringCase());
        Generator<Long> sondageId = Generator.of(Any.longInteger().inRange(Long.MIN_VALUE, Long.MAX_VALUE));
        Generator<Long> questionId = Generator.of(Any.longInteger().inRange(Long.MIN_VALUE, Long.MAX_VALUE));
        mvc.perform(post("/usagers/{userId}/sondage/{sondageId}/questions/{questionId}", userId.get(), sondageId.get(), questionId.get()))
            .andExpect(status().isCreated());
    }
}
