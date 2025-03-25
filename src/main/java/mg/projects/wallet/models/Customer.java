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

    @Column
    private Timestamp creation_date = new Timestamp(System.currentTimeMillis());
    
    @Column
    @InvalidConversion
    private String password;

    public Customer() {
        setDto(CustomerDTO.class);
    }
    public Customer(String name, String mail, String phone, String role, Timestamp creation_date) {
        setDto(CustomerDTO.class);
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creation_date = creation_date;
    }
    public Customer(String name, String mail, String phone, String role, Timestamp creation_date, String password) {
        setDto(CustomerDTO.class);
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
        this.creation_date = creation_date;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer [ customer , id="+this.getId()+", name=" + name + ", mail=" + mail + ", phone=" + phone
                + ", role=" + role + ", creation_date=" + creation_date + ", password=" + password + "]";
    }

   
    
}
