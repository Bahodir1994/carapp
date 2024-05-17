package uz.cars.carapp.service.helperClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto_v1 {
    private String fio;
    private String phone;
    private String carType;
    private String carNumber;
    private String carColor;
}
