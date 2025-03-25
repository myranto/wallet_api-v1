package mg.projects.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.Account;

public interface AccountRepo extends JpaRepository<Account, String> {

}
