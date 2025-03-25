package mg.projects.wallet.controller;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import mg.projects.wallet.models.Customer;
import mg.projects.wallet.services.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private CustomerService service;

    @Test
    void testDeleteById() {

    }

    @Test
    void testFindAllModel() throws Exception {
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        customer.setId("CUS003");
        customer.setPassword("sitraka");
        Customer customer2 =new Customer("Valye", "valy@gmail.com","09899", "C", new Timestamp(System.currentTimeMillis()));
        customer.setId("CUS004");
        customer.setPassword("valy");
        
        when(service.findAll(null)).thenReturn(List.of(customer, customer2));

        mockMVC.perform(MockMvcRequestBuilders.get("/customer"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Sitraka"));
    }

    @Test
    void testFindById() throws Exception {
        Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        p.setId("CUS003");
        p.setPassword("sitraka");
        when(service.findById("CUS003")).thenReturn(p);
        mockMVC.perform(MockMvcRequestBuilders.get("/customer/CUS003"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.mail").value("sitraka@gmail.com"));
    }

    @Test
    void testSaveModel() throws Exception {
        String json = """
                {
                    "id":"CUS003",
                    "name":"Sitraka",
                    "mail":"sitraka@gmail.com",
                    "phone":"09897",
                    "role":"C",
                    "creation_date":"2024-03-08T10:30",
                    "password":"sitraka"
                }
                """;
        Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        p.setId("CUS003");
        p.setPassword("sitraka");
        
        when(service.save(p)).thenReturn(p);

        mockMVC.perform(MockMvcRequestBuilders.post("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$").value("Sitraka"));
        
    }

    @Test
    void testUpdateModel() {

    }
}
