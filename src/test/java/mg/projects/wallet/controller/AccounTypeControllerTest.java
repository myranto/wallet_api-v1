package mg.projects.wallet.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Account_type;
import mg.projects.wallet.services.AccounTypeService;

@WebMvcTest(AccounTypeController.class)
public class AccounTypeControllerTest {

    @Autowired
    private MockMvc mockMVC;
    @MockBean
    private AccounTypeService service;
    private Page<DTO> page;
    private List<DTO> list;
    Account_type type = new Account_type("Staff", "STA", new Timestamp(System.currentTimeMillis()));
    Account_type type2 = new Account_type("Business", "BUS", new Timestamp(System.currentTimeMillis()));

    @BeforeEach
    void setUp() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        type.setId("ACT001");
        type2.setId("ACT002");
        list = List.of(type.EntityToDTO(), type2.EntityToDTO());
        page = new PageImpl<>(list);
    }

    @Test
    void testDeleteById() throws Exception {
        mockMVC.perform(MockMvcRequestBuilders.delete("/account_type/ACT001"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFindAllModel() throws Exception {
        when(service.findAll(null)).thenReturn(list);
        mockMVC.perform(MockMvcRequestBuilders.get("/account_type"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].type").value("Staff"));
    }

    @Test
    void testFindAllpaginateModel() throws Exception {
        when(service.getPaginated(0, 5, null)).thenReturn(page);
        mockMVC.perform(MockMvcRequestBuilders.get("/account_type/all/0/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].type").value("Staff"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[1].type").value("Business"));
    }

    @Test
    void testFindEntityByID() throws Exception {
        when(service.findById(type.getId())).thenReturn(type.EntityToDTO());
        mockMVC.perform(MockMvcRequestBuilders.get("/account_type/ACT001"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.type").value("Staff"));
    }

    @Test
    void testSaveModel() throws Exception {
        String json = """
                {
                    "id":"ACT001",
                    "type":"Staff",
                    "code":"STA"
                }
                """;
        when(service.save(any())).thenReturn(type);
        mockMVC.perform(
                MockMvcRequestBuilders.post("/account_type").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.type").value("Staff"));

    }

    @Test
    void testUpdateModel() throws Exception {
        String json = """
                {
                    "id":"ACT001",
                    "type":"Business",
                    "code":"BUS"
                }
                """;
        when(service.save(any())).thenReturn(type2);
        mockMVC.perform(
                MockMvcRequestBuilders.post("/account_type").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.type").value("Business"));
    }
}
