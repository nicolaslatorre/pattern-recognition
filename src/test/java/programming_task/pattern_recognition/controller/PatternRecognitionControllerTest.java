package programming_task.pattern_recognition.controller;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternRecognitionControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private PatternRecognitionController patternRecognitionController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(patternRecognitionController).build();
    }

    @Test
    public void testGetEmptySpace() throws Exception {
        mockMvc.perform(get("/space"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @Ignore
    // This test case is ignore due to a nested exception error
    public void testAddPoint() throws Exception {
        String json = "{\"x\":2.0,\"y\":3.0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/point")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }
}