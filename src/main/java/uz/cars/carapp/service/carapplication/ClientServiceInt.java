package uz.cars.carapp.service.carapplication;

import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.jwt.Jwt;
import uz.cars.carapp.entity.authorization.Users;
import uz.cars.carapp.entity.carapplication.ClientCars;
import uz.cars.carapp.service.helperClasses.ResponseDto_v1;

import java.util.List;

public interface ClientServiceInt {

    Page<Users> master_clients_list(int page, int size, String param, Jwt jwt);

    void save_master_clients(ResponseDto_v1 responseDtoV1, Jwt jwt) throws Throwable;

    void save_client_cars(String carType, String carNumber, String carColor, Users users);

    List<ClientCars> get_car_detail_by_client_id(String id);
}
