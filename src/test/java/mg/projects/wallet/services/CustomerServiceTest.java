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
import mg.projects.wallet.dto.CustomerDTO;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;

// classe unit service controller pour l'entity customer
@SpringBootTest
public class CustomerServiceTest {
    @Mock
    private CustomerRepo repo;

    @InjectMocks
    private CustomerService service;

    // @BeforeEach
    // void setUp(){
    //     MockitoAnnotations.openMocks(this);
    // }

    @Test
    void testDeleteById() throws Exception {
        // Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        // p.setId("CUS003");
        // p.setPassword("sitraka");
        service.deleteById("CUS003");
        // when(repo.deleteById("CUS003"))
        verify(repo).deleteById("CUS003");
    }

    @Test
    void testFindAll() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        customer.setId("CUS003");
        customer.setPassword("sitraka");
        Customer customer2 =new Customer("Valye", "valy@gmail.com","09899", "C", new Timestamp(System.currentTimeMillis()));
        customer2.setId("CUS004");
        customer2.setPassword("valy");

        when(repo.findAll()).thenReturn(List.of(customer, customer2));

        List<DTO> list = service.findAll(null);
        Assertions.assertThat(list).hasSize(2)
        .satisfiesExactly(
            dto1 -> Assertions.assertThat(dto1.getId()).isEqualTo(customer.getId()),
            dto2 -> Assertions.assertThat(dto2.getId()).isEqualTo(customer2.getId())  
        );
        // .containsExactly(customer.EntityToDTO(), customer2.EntityToDTO());
    }

    @Test
    void testFindById() throws Exception {
        Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        p.setId("CUS003");
        p.setPassword("sitraka");

        when(repo.findById("CUS003")).thenReturn(Optional.of(p));

        CustomerDTO finded = (CustomerDTO) service.findById("CUS003");
        assertEquals(p.EntityToDTO().getId(), finded.getId());
    }

    @Test
    void testGetPaginated() {

    }

    @Test
    void testSaveAndUpdate() {
        Customer p =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        p.setId("CUS003");
        p.setPassword("sitraka");
        when(repo.save(p)).thenReturn(p);
        Customer person = service.save(p);
        Assertions.assertThat(person).isEqualTo(p);
    }
}
