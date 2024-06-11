package uz.cars.carapp.service.helperClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto_v4 {
    private String id;
    private String pay;
    private Boolean isDebt;

    public BigDecimal getPayBigDecimal() {
        if (this.pay != null && !this.pay.equals("")){
            return new BigDecimal(this.pay);
        }else return BigDecimal.ZERO;
    }
}
