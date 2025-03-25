package mg.projects.wallet.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Account_type;

@Getter
@Setter
public class AccounTypeDTO extends DTO {
    
    private String type;
    
    private String value;
    
    private Timestamp creation_date;

    public AccounTypeDTO() {
        setEntity(Account_type.class);
    }

    public AccounTypeDTO(String type, String value, Timestamp creation_date) {
        this.type = type;
        this.value = value;
        this.creation_date = creation_date;
        setEntity(Account_type.class);
    }
    
}
