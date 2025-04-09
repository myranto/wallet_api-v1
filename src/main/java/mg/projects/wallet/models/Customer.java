package mg.projects.wallet.models;

import java.sql.Timestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.DtoConversion.InvalidConversion;
import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.dto.CustomerDTO;

@Getter
@Setter
@Entity
public class Customer extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String mail;

    
    @Column
    private String phone;

    @Column(nullable = false)
    private String role;

    @Column(name = "creation_date")
    private Timestamp creationdate = new Timestamp(System.currentTimeMillis());
    
    @Column
    @InvalidConversion
    private String password;

    public Customer() {
        setDto(CustomerDTO.class);
    }
    public Customer(String name, String mail, String phone, String role, Timestamp creationdate) {
        setDto(CustomerDTO.class);
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creationdate = creationdate;
    }
    
    public Customer(String name, String mail, String phone, String role, Timestamp creationdate, String password) {
        setDto(CustomerDTO.class);
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creationdate = creationdate;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer [ customer , id="+this.getId()+", name=" + name + ", mail=" + mail + ", phone=" + phone
                + ", role=" + role + ", creationdate=" + creationdate + ", password=" + password + "]";
    }

   
    
}
