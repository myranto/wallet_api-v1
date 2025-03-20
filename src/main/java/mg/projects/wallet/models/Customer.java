package mg.projects.wallet.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.BaseEntity;
import mg.projects.wallet.dto.CustomerDTO;

@Getter
@Setter
@AllArgsConstructor
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
    private String password;

    public Customer() {
    }
    public Customer DTOtoCustomer(CustomerDTO dto){
        Customer news = new Customer(dto.getName(), dto.getMail(), dto.getPhone(), dto.getRole(), dto.getCreation_date(), dto.getPassword());
        news.setId(dto.getId());
        return news;
    }
    
}
