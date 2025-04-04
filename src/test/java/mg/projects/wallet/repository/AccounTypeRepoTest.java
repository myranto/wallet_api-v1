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

import mg.projects.wallet.models.Account_type;

@DataJpaTest
@Sql("/data.sql")
public class AccounTypeRepoTest {
    @Autowired
    private AccounTypeRepo repo;

    @Test
    void testFindById(){
        Optional<Account_type> find = repo.findById("ACT001");
        assertTrue(find.isPresent());
        assertEquals("Courant", find.get().getType());
    }
    @Test
    void testFindAll(){
        List<Account_type> list = repo.findAll();
        assertEquals(2, list.size());
        assertEquals("ACT001", list.get(0).getId());
        assertEquals("ACT002", list.get(1).getId());
    }
    @Test
    void testSaveAndUpdate(){
        Account_type type = new Account_type("Staff", "STA", new Timestamp(System.currentTimeMillis()));
        type.setId("ACT002");

        Account_type saved = repo.save(type);
        assertNotNull(saved);
        assertEquals("Staff", saved.getType());

        saved.setType("Business");
        saved.setCode("BUS");
        repo.save(saved);

        Optional<Account_type> updated = repo.findById("ACT002");
        assertTrue(updated.isPresent());
        assertEquals("Business", updated.get().getType());
    }
    @Test
    void testDelete(){
        repo.deleteById("ACT002");
        Optional<Account_type> toDelete = repo.findById("ACT002");
        assertFalse(toDelete.isPresent());

    }
}
