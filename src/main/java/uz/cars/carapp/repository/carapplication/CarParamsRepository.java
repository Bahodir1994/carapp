package uz.cars.carapp.repository.carapplication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.cars.carapp.entity.carapplication.CarParams;
import uz.cars.carapp.entity.carapplication.ClientCars;

import java.util.List;

@Repository
public interface CarParamsRepository extends JpaRepository<CarParams, String> {
    List<CarParams> findByClientCars(ClientCars clientCars);

    List<CarParams> findAll(Specification<ClientCars> specification);

    @Modifying
    @Transactional
    @Query("UPDATE CarParams e SET e.isActually = :isActually WHERE e.clientCars = :clientCars")
    void updateAllByClientCars(@Param("clientCars") ClientCars clientCars, @Param("isActually") boolean isActually);

    Page<CarParams> findAll(Specification<CarParams> specification_v1, Pageable pageable);
}
