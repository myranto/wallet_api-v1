package mg.projects.wallet.common.baseModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.DtoConversion.ConversionService;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;

// class qui hérite de conversion service afin d'executer la méthode de conversion
// de manière uniforme pour tous les dto
@MappedSuperclass
@Getter
@Setter
public class DTO extends ConversionService{
    private String id;

    private Timestamp creationdate ;
    private int status;

    @InvalidConversion
    private Class<?> entity;

    public BaseEntity dtoToBaseEntity() throws InstantiationException, IllegalAccessException, InvocationTargetException{
        return (BaseEntity) this.ObjectConversion(entity, this);
    }
}
