package mg.projects.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import mg.projects.wallet.models.Customer;

// classe unit test repository pour l'entity customer
@DataJpaTest
@Sql("/data.sql")
public class CustomerRepoTest {

    @Autowired
    private CustomerRepo repo;
    // etapes
        // arrange -- preparation data
        // act -- appel de methode
        // assert -- verification des resultats
    @Test
    void testFindOneByMail() {
        Optional<Customer> find = repo.findOneByMail("my.randrianantoandro@gmail.com");
        assertTrue(find.isPresent());
        // assertEquals("my.randrianantoandro@gmail.com", find.getMail());
    }
    @Test
    void shouldFindOneById(){
        Customer finded = repo.findById("CUS002").orElse(repo.findOneByMail("larry.fah@gmail.com").orElse(null));
        assertEquals("larry.fah@gmail.com", finded.getMail());
    }

    @Test
    void testFindAll(){
        List<Customer> list = repo.findAll();
        System.out.println(list.size());
        assertEquals(3, list.size());
    }
    @Test
    void testSaveAndUpdate(){
        Customer customer =new Customer("Sitraka", "sitraka@gmail.com","09897", "C", new Timestamp(System.currentTimeMillis()));
        customer.setId("CUS003");
        customer.setPassword("sitraka");
        Customer vao = repo.save(customer);
        assertNotNull(vao);
        assertEquals("Sitraka", vao.getName());

        customer.setName("Randria");
        repo.save(customer);
        Customer updated = repo.findOneByMail("sitraka@gmail.com").orElse(null);
        assertNotNull(updated);
        assertEquals("Randria", updated.getName());   
    }
    @Test
    void testDelete(){
        repo.deleteById("CUS002");
        Optional<Customer> toDelete = repo.findOneByMail("larry.fah@gmail.com");
        assertFalse(toDelete.isPresent());

    }
}
