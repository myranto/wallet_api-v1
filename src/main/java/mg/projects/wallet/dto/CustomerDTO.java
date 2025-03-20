package mg.projects.wallet.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.projects.wallet.models.Customer;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private String id;
    private String name;
    private String mail;
    private String phone;
    private String role;
    private String password;
    private Timestamp creation_date;
    
    public CustomerDTO(String id, String name, String mail, String phone, String role, Timestamp creation_date) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creation_date = creation_date;
    }

    public CustomerDTO modelsToDTO(Customer toConvert){
        return new CustomerDTO(toConvert.getId(), toConvert.getName(), toConvert.getMail(), toConvert.getPhone(), toConvert.getRole(), toConvert.getCreation_date());
    }

}