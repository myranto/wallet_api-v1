package mg.projects.wallet.common;

import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.*;
import mg.projects.wallet.common.DtoConversion.ConversionService;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;

@MappedSuperclass
public class BaseEntity extends ConversionService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @InvalidConversion
    private Class<?> dto;
    
    
    public void setDto(Class<?> dto){
        this.dto = dto;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public DTO EntityToDTO() throws InstantiationException, IllegalAccessException, InvocationTargetException{
        return (DTO) this.ObjectConversion(dto, this);
    }
}
