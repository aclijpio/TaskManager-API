package ru.pio.aclij.taskmanagerapi;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.pio.aclij.taskmanagerapi.security.AccountController;
import ru.pio.aclij.taskmanagerapi.security.AuthController;
import ru.pio.aclij.taskmanagerapi.security.service.AuthService;
import ru.pio.aclij.taskmanagerapi.security.utils.JwtTokenUtils;


@RunWith(Enclosed.class)
public class SecurityTest {
    @RunWith(SpringRunner.class)
    @WebMvcTest({AuthController.class, AccountController.class})
    public static class AuthControllerTest {

        private MockMvc mockMvc;

        private AuthService authService;
        private JwtTokenUtils jwtTokenUtils;

        @Test
        public void testCreateAuthToken() throws Exception {

        }
    }
}
