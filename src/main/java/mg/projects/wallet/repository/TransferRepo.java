package mg.projects.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.Transfer;

public interface TransferRepo extends JpaRepository<Transfer, String>{

    
}
