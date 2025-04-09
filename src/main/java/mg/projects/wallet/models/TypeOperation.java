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
    @Column(nullable = false)
    private String libelle;
    @Column(nullable = false)
    private String code;
    public TypeOperation() {
        setDto(TypeOperationDTO.class);
    }
    public TypeOperation(String libelle, String code) {
        this.libelle = libelle;
        this.code = code;
        setDto(TypeOperationDTO.class);
    }
    
}
