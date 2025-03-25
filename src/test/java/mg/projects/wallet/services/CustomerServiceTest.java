package mg.projects.wallet.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;

@SpringBootTest
public class CustomerServiceTest {
    @Mock
    private CustomerRepo repo;

    @InjectMocks
    private CustomerService service;

    @Test
    void testDelete() {

    }

    @Test
    void testDeleteById() {

    }

    @Test
    void testFindAll() {
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        customer.setId("CUS003");
        customer.setPassword("sitraka");
        Customer customer2 =new Customer("Valye", "valy@gmail.com","09899", "C", new Timestamp(System.currentTimeMillis()));
        customer.setId("CUS004");
        customer.setPassword("valy");

        when(repo.findAll()).thenReturn(List.of(customer, customer2));

        List<Customer> list = service.findAll(null);
        assertEquals(2, list.size());
    }

    @Test
    void testFindById() {

    }

    @Test
    void testGetJpa() {

    }

    @Test
    void testGetPageRow() {

    }

    @Test
    void testGetPaginated() {

    }

    @Test
    void testSave() {

    }
}
