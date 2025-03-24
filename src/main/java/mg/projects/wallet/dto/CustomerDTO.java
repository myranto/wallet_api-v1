package mg.projects.wallet.dto;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.projects.wallet.common.DTO;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;
import mg.projects.wallet.models.Customer;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO extends DTO<Customer>{

    private String name;
    private String mail;
    private String phone;
    private String role;
    private String password;
    private Timestamp creation_date;
    
    public CustomerDTO(String name, String mail, String phone, String role, Timestamp creation_date) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creation_date = creation_date;
    }

    // public Customer DTOtoCustomer(CustomerDTO dto){
    //     Customer news = new Customer(dto.getName(), dto.getMail(), dto.getPhone(), dto.getRole(), dto.getCreation_date());
    //     news.setId(dto.getId());
    //     return news;
    // }

    @Override
    public String toString() {
        return "CustomerDTO [ DTO,  name=" + name + ", mail=" + mail + ", phone="
                + phone + ", role=" + role + ", password=" + password + ", creation_date=" + creation_date + "]";
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        CustomerDTO dto = new CustomerDTO( "My Ranto", "mmm@gmail.com", "39242", "Admin", new Timestamp(System.currentTimeMillis()));
        Customer cli = (Customer) dto.ObjectConversion(Customer.class, dto);
        System.out.println(cli.toString());
    }
}