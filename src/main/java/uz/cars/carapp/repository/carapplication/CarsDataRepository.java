package uz.cars.carapp.repository.carapplication;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;
import uz.cars.carapp.entity.carapplication.ClientCars;

@Repository
public interface CarsDataRepository extends DataTablesRepository<ClientCars, String> {
}
