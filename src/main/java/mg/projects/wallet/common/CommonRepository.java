package mg.projects.wallet.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T, ID> extends JpaRepository<T, ID>{
    List<T> findAllByStatus(int status);
    Page<T> findAllByStatus(int status, Pageable page);
    Optional<T> findByIdAndStatus(ID id, int status);
}
