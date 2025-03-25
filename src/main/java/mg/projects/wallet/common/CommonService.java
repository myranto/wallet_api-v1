package mg.projects.wallet.common;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.common.baseModel.BaseEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public class CommonService <T extends BaseEntity,ID,JPA extends JpaRepository<T,ID>> {
    private final JPA jpa;
    public CommonService(JPA jpa) {
        this.jpa = jpa;
    }
    public JPA getJpa() {
        return jpa;
    }

    public T save(T model){
        return jpa.save(model);
    }
    public void delete(T model){
        jpa.delete(model);
    }
    public void deleteById(ID id){
        jpa.deleteById(id);
    }
    public T findById(ID id){
        return jpa.findById(id).orElse(null);
    }
    public List<T> findAll(String key){
        if (key == null || key.equals("")) {
           return jpa.findAll();
        }
        return null;
    }
    
    public int[] getPageRow(int pageNumber, int pageSize) {
        long totalRecords = getJpa().count();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageNumber > totalPages) {
            pageNumber = totalPages;
        }
        int offset = (pageNumber - 1) * pageSize;
        if (offset > totalRecords) {
            offset = (int) totalRecords;
        }
        return new int[]{offset + 1, offset + pageSize};
    }
    public Page<T> getPaginated(int pageNumber, int pageSize, String key) {
       Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return jpa.findAll(pageable);
    }

}
