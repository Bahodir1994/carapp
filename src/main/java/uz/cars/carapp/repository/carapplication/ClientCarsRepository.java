package uz.cars.carapp.repository.carapplication;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cars.carapp.entity.authorization.Users;
import uz.cars.carapp.entity.carapplication.CarParams;
import uz.cars.carapp.entity.carapplication.ClientCars;

import java.util.List;

@Repository
public interface ClientCarsRepository extends JpaRepository<ClientCars, String>{
    Page<ClientCars> findAll(Pageable pageable);

    Page<ClientCars> findAll(Specification<ClientCars> specification, Pageable pageable);

    List<ClientCars> findAllByUsers(Users users);

    List<ClientCars> findAll(Specification<ClientCars> specification);
}
