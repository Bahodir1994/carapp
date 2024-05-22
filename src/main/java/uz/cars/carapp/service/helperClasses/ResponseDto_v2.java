package uz.cars.carapp.service.helperClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<String> filters;
}
