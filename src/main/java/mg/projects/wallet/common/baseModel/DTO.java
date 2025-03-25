package mg.projects.wallet.common.baseModel;

import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.MappedSuperclass;
import mg.projects.wallet.common.DtoConversion.ConversionService;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;

// class qui hérite de conversion service afin d'executer la méthode de conversion
// de manière uniforme pour tous les dto
@MappedSuperclass
public class DTO extends ConversionService{
    private String id;

    @InvalidConversion
    private Class<?> entity;

    public void setEntity(Class<?> entity) {
        this.entity = entity;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public BaseEntity dtoToBaseEntity() throws InstantiationException, IllegalAccessException, InvocationTargetException{
        return (BaseEntity) this.ObjectConversion(entity, this);
    }
}
