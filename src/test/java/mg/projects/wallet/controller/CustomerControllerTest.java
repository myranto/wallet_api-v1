package mg.projects.wallet.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import mg.projects.wallet.models.Customer;
import mg.projects.wallet.services.CustomerService;
// classe unit test controller pour l'entity customer
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private CustomerService service;

    @Test
    void testDeleteById() throws Exception {
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C");
        customer.setId("CUS003");
        customer.setPassword("sitraka");

        mockMVC.perform(MockMvcRequestBuilders.delete("/customer/CUS003"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFindAllModel() throws Exception {
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C");
        customer.setId("CUS003");
        customer.setPassword("sitraka");
        Customer customer2 =new Customer("Valye", "valy@gmail.com","09899", "C");
        customer.setId("CUS004");
        customer.setPassword("valy");
        
        when(service.findAll(null)).thenReturn(List.of(customer.EntityToDTO(), customer2.EntityToDTO()));

        mockMVC.perform(MockMvcRequestBuilders.get("/customer"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Sitraka"));
    }

    @Test
    void testFindById() throws Exception {
        Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C");
        p.setId("CUS003");
        p.setPassword("sitraka");
        when(service.findById("CUS003")).thenReturn(p.EntityToDTO());
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
                    "password":"sitraka",
                    "creationdate":"2024-03-08T10:30"
                }
                """;
        Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", null);
        p.setId("CUS003");
        p.setPassword("sitraka");
        
        when(service.save(any())).thenReturn(p);

        mockMVC.perform(MockMvcRequestBuilders.post("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Sitraka"));
        
    }

    @Test
    void testUpdateModel() throws Exception {
        String json = """
                {
                    "id":"CUS003",
                    "name":"Sitraka",
                    "mail":"sitraka@gmail.com",
                    "phone":"09897",
                    "role":"C",
                    "creationdate":"2024-03-08T10:30",
                    "password":"ketrika"
                }
                """;
        Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C");
        p.setId("CUS003");
        p.setPassword("sitraka");
        Customer b =new Customer("Sitraka", "sitraka@gmail.com","09897", "C");
        b.setId("CUS003");
        b.setPassword("ketrika");
        when(service.save(p)).thenReturn(b);
        
        mockMVC.perform(MockMvcRequestBuilders.put("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
