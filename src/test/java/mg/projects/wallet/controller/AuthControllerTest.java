package mg.projects.wallet.controller;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import mg.projects.wallet.dto.AuthDTO;
import mg.projects.wallet.dto.CustomerDTO;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.services.AuthService;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private AuthService service;

    @Test
    void testAuthentication() throws Exception {
        String json = """
                {
                    "mail":"sitraka@gmail.com",
                    "password":"sitraka"
                }
                """;
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        customer.setId("CUS003");
        customer.setPassword("sitraka");
        
        AuthDTO auth = new AuthDTO("sitraka@gmail.com", "sitraka");
        
        when(service.checkPassword(auth)).thenReturn((CustomerDTO)customer.EntityToDTO());

        mockMVC.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isOk());
        // .andExpect(MockMvcResultMatchers.jsonPath("$.data.mail").value("sitraka@gmail.com"));
    }

    @Test
    void testresetPassword() throws Exception {
        String json = """
            {
                "mail":"sitraka@gmail.com",
                "password":"sitraka",
                "pwd":"sitraka"
            }
            """;
        AuthDTO auth = new AuthDTO("sitraka@gmail.com", "sitraka");
        auth.setPwd("sitraka");
        when(service.resetPassword(auth)).thenReturn("Modification réussi");
        mockMVC.perform(MockMvcRequestBuilders.post("/auth/reset/password").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
