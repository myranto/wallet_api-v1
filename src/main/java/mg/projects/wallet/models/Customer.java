package mg.projects.wallet.models;

import java.sql.Timestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.BaseEntity;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;
import mg.projects.wallet.dto.CustomerDTO;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Customer extends BaseEntity<CustomerDTO>{

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column
    private String phone;

    @Column(nullable = false)
    private String role;

    @Column
    private Timestamp creation_date = new Timestamp(System.currentTimeMillis());
    
    @Column
    @InvalidConversion
    private String password;

    public Customer() {
    }
    public Customer(String name, String mail, String phone, String role, Timestamp creation_date) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creation_date = creation_date;
    }
    
    public CustomerDTO modelsToDTO(){
        CustomerDTO dto =  new CustomerDTO(this.getName(), this.getMail(), this.getPhone(), this.getRole(), this.getCreation_date());
        dto.setId(this.getId());
        return dto;
    }
    @Override
    public String toString() {
        return "Customer [ customer name=" + name + ", mail=" + mail + ", phone=" + phone
                + ", role=" + role + ", creation_date=" + creation_date + ", password=" + password + "]";
    }

   
    
}
