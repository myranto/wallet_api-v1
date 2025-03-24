package mg.projects.wallet.common;

import jakarta.persistence.MappedSuperclass;
import mg.projects.wallet.common.DtoConversion.ConversionService;

@MappedSuperclass
public class DTO<B extends Object> extends ConversionService{
    private String id;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
