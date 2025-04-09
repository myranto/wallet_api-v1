package mg.projects.wallet.common.baseModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.DtoConversion.ConversionService;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;

// class qui hérite de conversion service afin d'executer la méthode de conversion
// de manière uniforme pour tous les entity
@MappedSuperclass
@Getter
@Setter
public class BaseEntity extends ConversionService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private int status;
    @Column(name = "creation_date")
    private Timestamp creationdate = new Timestamp(System.currentTimeMillis());
    
    @InvalidConversion
    @Transient
    private Class<?> dto;
    
    public DTO EntityToDTO() throws InstantiationException, IllegalAccessException, InvocationTargetException{
        return (DTO) this.ObjectConversion(dto, this);
    }
}
