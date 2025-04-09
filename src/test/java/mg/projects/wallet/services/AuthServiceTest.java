package mg.projects.wallet.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import mg.projects.wallet.dto.AuthDTO;
import mg.projects.wallet.dto.CustomerDTO;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;

@SpringBootTest
public class AuthServiceTest {
    @Mock
    private CustomerRepo repo;

    @InjectMocks
    private AuthService service;

    @Test
    void testCheckPassword() throws Exception {
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C");
        customer.setId("CUS003");
        customer.setPassword("sitraka");
        
        AuthDTO auth = new AuthDTO("sitraka@gmail.com", "sitraka");
        
        when(repo.findOneByMail(auth.getMail())).thenReturn(Optional.of(customer));
        
        CustomerDTO logged = service.checkPassword(auth);
        
        assertEquals(customer.getId(), logged.getId());

    }

    @Test
    void testResetPassword() throws Exception {
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C");
        customer.setId("CUS003");
        customer.setPassword("sitraka");

        AuthDTO auth = new AuthDTO("sitraka@gmail.com", "sitraka");
        auth.setPwd("sitraka");

        when(repo.findOneByMail(auth.getMail())).thenReturn(Optional.of(customer));
        when(repo.save(customer)).thenReturn(customer);

        String updating = service.resetPassword(auth);
        assertEquals("Modification réussi", updating);
    }
}
