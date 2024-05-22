package uz.cars.carapp.repository.carapplication;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cars.carapp.entity.carapplication.CarParams;
import uz.cars.carapp.entity.carapplication.ClientCars;

import java.util.List;

@Repository
public interface CarParamsRepository extends JpaRepository<CarParams, String> {
    List<CarParams> findByClientCars(ClientCars clientCars);

    List<CarParams> findAll(Specification<ClientCars> specification);
}
