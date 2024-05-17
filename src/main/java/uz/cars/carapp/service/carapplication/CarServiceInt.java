package uz.cars.carapp.service.carapplication;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.oauth2.jwt.Jwt;
import uz.cars.carapp.entity.carapplication.ClientCars;

public interface CarServiceInt {
    Page<ClientCars> cars_list(int page, int size, String param, Jwt jwt);

    DataTablesOutput<ClientCars> cars_data_list(DataTablesInput dataTablesInput, String param);
}
