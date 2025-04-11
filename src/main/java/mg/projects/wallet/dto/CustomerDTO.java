package mg.projects.wallet.dto;


import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Customer;

@Getter
@Setter
public class CustomerDTO extends DTO{

    private String name;
    private String mail;
    private String phone;
    private String role;
    private String password;
    @InvalidConversion
    private String token;

    public CustomerDTO(){
        this.setEntity(Customer.class);
    }
    public CustomerDTO(String name, String mail, String phone, String role, String token) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.token = token;
        this.setEntity(Customer.class);
    }

    @Override
    public String toString() {
        return "CustomerDTO [ DTO,  id="+this.getId()+", name=" + name + ", mail=" + mail + ", phone="
                + phone + ", role=" + role + ", password=" + password + "]";
    }
}