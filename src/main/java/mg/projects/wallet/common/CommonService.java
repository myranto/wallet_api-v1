package mg.projects.wallet.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.common.baseModel.DTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
 * Classe dont tous les service entity réalisant un crud
 * devront hériter pour ne pas avoir de répétition de code
 * et avoir une performance inouie
 */
public class CommonService <T extends BaseEntity,ID,JPA extends CommonRepository<T,ID>> {
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
    public void deleteById(ID id) throws Exception{
        T model = jpa.findByIdAndStatus(id, 0).orElseThrow(() -> new Exception("Id introuvable "+id));
        model.setStatus(1);
        jpa.save(model);
        // jpa.deleteById(id);
    }
    public DTO findById(ID id) throws Exception{
        T finded = jpa.findByIdAndStatus(id, 0).orElseThrow(() -> new Exception("Id introuvable "+id));
        return finded.EntityToDTO();
    }
    public List<DTO> findAll(String key) throws InstantiationException, IllegalAccessException, InvocationTargetException{
        if (key == null || key.equals("")) {
           return ListEntityToListDto(jpa.findAllByStatus(0));
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
    /*
     * Conversion de page entity to page dto
     * encore à tester une fois à la maison
     */
    public Page<DTO> getPaginated(int pageNumber, int pageSize, String key) {
       Pageable pageable = PageRequest.of(pageNumber, pageSize);
       Page<T> list = jpa.findAllByStatus(0,pageable);
       Page<DTO> result = list.map(entity -> {
        try {
            return entity.EntityToDTO();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    });
        return result;
    }

    // function de conversion d'une liste de entity vers une liste de dto
    private List<DTO> ListEntityToListDto(List<T> list) throws InstantiationException, IllegalAccessException, InvocationTargetException{
        List<DTO> result = new ArrayList<DTO>();
        for (T row : list) {
            result.add(row.EntityToDTO());
        }
        return result;
    }

}
