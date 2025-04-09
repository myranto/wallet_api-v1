package mg.projects.wallet.dto;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
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
    private Timestamp creationdate;
    public CustomerDTO(){
        this.setEntity(Customer.class);
    }
    public CustomerDTO(String name, String mail, String phone, String role, Timestamp creationdate) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creationdate = creationdate;
        this.setEntity(Customer.class);
    }

    @Override
    public String toString() {
        return "CustomerDTO [ DTO,  id="+this.getId()+", name=" + name + ", mail=" + mail + ", phone="
                + phone + ", role=" + role + ", password=" + password + ", creationdate=" + creationdate + "]";
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        CustomerDTO dto = new CustomerDTO( "My Ranto", "mmm@gmail.com", "39242", "Admin", new Timestamp(System.currentTimeMillis()));
        dto.setId("CUS001");
        Customer cli = (Customer) dto.dtoToBaseEntity();
        System.out.println(cli.toString());
        System.out.println("reconversion inversé");
        CustomerDTO reconvert = (CustomerDTO) cli.EntityToDTO();
        System.out.println(reconvert.toString());
    }
}