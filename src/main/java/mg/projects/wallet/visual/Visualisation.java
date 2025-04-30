package mg.projects.wallet.visual;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Visualisation {
    private List<VamountDTO> credit;
    private List<VamountDTO> charge;
    private Timestamp month;
    private BigDecimal totalAmount;
}
