package mg.projects.wallet.dto;


import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Account_type;

@Getter
@Setter
public class AccounTypeDTO extends DTO {
    
    private String type;
    
    private String code;
    

    public AccounTypeDTO() {
        setEntity(Account_type.class);
    }

    public AccounTypeDTO(String type, String code) {
        this.type = type;
        this.code = code;
        setEntity(Account_type.class);
    }
    
}
