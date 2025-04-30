package mg.projects.wallet.visual;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VamountDTO {
    private String customer_id;
    private BigDecimal amount;
    private String account_id;
    public VamountDTO() {
    }
    public VamountDTO(String customer_id, BigDecimal amount, String account_id) {
        this.customer_id = customer_id;
        this.amount = amount;
        this.account_id = account_id;
    }

    

}
