package mg.projects.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.Charge;

public interface ChargeRepo extends JpaRepository<Charge, String>{

}
