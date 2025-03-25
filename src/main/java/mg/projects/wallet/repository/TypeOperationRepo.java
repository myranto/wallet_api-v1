package mg.projects.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.TypeOperation;

public interface TypeOperationRepo extends JpaRepository<TypeOperation, String> {

}
