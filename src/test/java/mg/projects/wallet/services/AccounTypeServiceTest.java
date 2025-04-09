package mg.projects.wallet.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.dto.AccounTypeDTO;
import mg.projects.wallet.models.Account_type;
import mg.projects.wallet.repository.AccounTypeRepo;

@SpringBootTest
public class AccounTypeServiceTest {
    @Mock
    private AccounTypeRepo repo;

    @InjectMocks
    private AccounTypeService service;

    @Test
    void testDeleteById() throws Exception {
        Account_type type = new Account_type("Staff", "STA", new Timestamp(System.currentTimeMillis()));
        type.setId("ACT002");
        when(repo.save(type)).thenReturn(type);
        Account_type saved = service.save(type);
        Assertions.assertThat(saved).isEqualTo(type);
        service.deleteById("ACT002");
        verify(repo).deleteById("ACT002");
        

    }

    @Test
    void testFindAll() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Account_type type = new Account_type("Staff", "STA", new Timestamp(System.currentTimeMillis()));
        type.setId("ACT001");
        Account_type type2 = new Account_type("Business", "BUS", new Timestamp(System.currentTimeMillis()));
        type.setId("ACT002");
        when(repo.findAll()).thenReturn(List.of(type, type2));

        List<DTO> list = service.findAll(null);
        Assertions.assertThat(list).hasSize(2)
                .satisfiesExactly(
                        r -> assertEquals(type.getId(), r.getId()),
                        e -> assertEquals(type2.getId(), e.getId()));
    }

    @Test
    void testFindById() throws Exception {
        Account_type type = new Account_type("Staff", "STA", new Timestamp(System.currentTimeMillis()));
        type.setId("ACT002");
        when(repo.findById(type.getId())).thenReturn(Optional.of(type));
        AccounTypeDTO finded = (AccounTypeDTO) service.findById(type.getId());
        assertEquals(type.getId(), finded.getId());
    }

    @Test
    void testGetPaginated() {

    }

    @Test
    void testSaveAndUpdate() {
        Account_type type = new Account_type("Staff", "STA", new Timestamp(System.currentTimeMillis()));
        type.setId("ACT002");
        when(repo.save(type)).thenReturn(type);
        Account_type saved = service.save(type);
        Assertions.assertThat(saved).isEqualTo(type);
    }
}
