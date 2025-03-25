package mg.projects.wallet.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.dto.TypeOperationDTO;

@Entity(name = "type_operation")
@Getter
@Setter
public class TypeOperation extends BaseEntity{
    @Column
    private String libelle;
    @Column
    private String value;
    @Column
    private Timestamp creation_date = new Timestamp(System.currentTimeMillis());
    public TypeOperation() {
        setDto(TypeOperationDTO.class);
    }
    public TypeOperation(String libelle, String value, Timestamp creation_date) {
        this.libelle = libelle;
        this.value = value;
        this.creation_date = creation_date;
        setDto(TypeOperationDTO.class);
    }
    
}
