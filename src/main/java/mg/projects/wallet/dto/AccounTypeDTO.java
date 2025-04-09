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
    
    private String code;
    
    private Timestamp creationdate;

    public AccounTypeDTO() {
        setEntity(Account_type.class);
    }

    public AccounTypeDTO(String type, String code, Timestamp creationdate) {
        this.type = type;
        this.code = code;
        this.creationdate = creationdate;
        setEntity(Account_type.class);
    }
    
}
