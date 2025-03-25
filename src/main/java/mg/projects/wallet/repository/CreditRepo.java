package mg.projects.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.Credit;

public interface CreditRepo extends JpaRepository<Credit, String> {

}
