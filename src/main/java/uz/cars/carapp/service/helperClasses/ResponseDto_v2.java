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
public class ResponseDto_v2 {
    private String carId;
    private String currentDistance;
    private String distanceTo;
    private String oilType;
    private String oilQuantity;
    private String serviceCharge;
    private String payedCharge;
    private List<String> filters;

    public BigDecimal getServiceChargeBigDecimal() {
        if (this.serviceCharge != null && !this.serviceCharge.equals("")){
            return new BigDecimal(this.serviceCharge);
        }else return BigDecimal.ZERO;
    }

    public BigDecimal getPayedChargeBigDecimal() {
        if (this.payedCharge != null && !this.payedCharge.equals("")){
            return new BigDecimal(this.payedCharge);
        }else return BigDecimal.ZERO;
    }
}
