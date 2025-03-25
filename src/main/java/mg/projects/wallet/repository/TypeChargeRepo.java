package mg.projects.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.TypeCharge;

public interface TypeChargeRepo extends JpaRepository<TypeCharge, String> {

}
