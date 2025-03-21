package mg.projects.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.Account_type;

/*
 * Création repo account_type in local
 */
public interface AccounTypeRepo extends JpaRepository<Account_type, String>{

}
