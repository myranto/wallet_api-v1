package mg.projects.wallet.common;

import jakarta.persistence.*;
import mg.projects.wallet.common.DtoConversion.ConversionService;

@MappedSuperclass
public class BaseEntity<D extends Object> extends ConversionService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
