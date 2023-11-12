package ru.pio.aclij.taskmanagerapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.WebUtils;
import ru.pio.aclij.taskmanagerapi.taskManager.TaskController;
import ru.pio.aclij.taskmanagerapi.taskManager.TasksService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
@RequiredArgsConstructor
public class TaskControllerTest {

    private MockMvc mockMvc;
    protected ObjectMapper objectMapper;
    private TasksService service;

    @Test
    public void testTask() throws Exception {



    }

}