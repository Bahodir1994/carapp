package uz.cars.carapp.service.carapplication;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import uz.cars.carapp.entity.carapplication.ClientCars;

public interface CarServiceInt {
    Page<ClientCars> cars_list(int page, int size);

    DataTablesOutput<ClientCars> cars_data_list(DataTablesInput dataTablesInput);
}
