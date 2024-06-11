package uz.cars.carapp.service.carapplication;

import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.jwt.Jwt;
import uz.cars.carapp.entity.carapplication.CarParams;
import uz.cars.carapp.service.helperClasses.ResponseDto_v2;

public interface CarParamsInt {
    void save_car_params_by_user_id(ResponseDto_v2 responseDtoV2);

    Page<CarParams> getPaginatedData(int page, int size, String param, Jwt jwt);
}
