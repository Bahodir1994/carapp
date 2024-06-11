package uz.cars.carapp.service.carapplication;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import uz.cars.carapp.entity.authorization.Roles;
import uz.cars.carapp.entity.authorization.Users;
import uz.cars.carapp.entity.carapplication.CarParams;
import uz.cars.carapp.entity.carapplication.ClientCars;
import uz.cars.carapp.entity.carapplication.Payment;
import uz.cars.carapp.repository.carapplication.CarParamsRepository;
import uz.cars.carapp.repository.carapplication.ClientCarsRepository;
import uz.cars.carapp.service.helperClasses.ResponseDto_v2;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarParamsService implements CarParamsInt{
    private final ClientCarsRepository clientCarsRepository;
    private final CarParamsRepository carParamsRepository;
    private final PaymentService paymentService;

    @Override
    public void save_car_params_by_user_id(ResponseDto_v2 responseDtoV2) {
        CarParams carParams = new CarParams();

        ClientCars clientCars = clientCarsRepository.findById(responseDtoV2.getCarId()).orElse(new ClientCars());
        carParamsRepository.updateAllByClientCars(clientCars, false);

        carParams.setClientCars(clientCars);
        carParams.setMileage(Double.parseDouble(responseDtoV2.getCurrentDistance()));
        carParams.setToMileage(Double.parseDouble(responseDtoV2.getDistanceTo()));
        carParams.setOilModel(responseDtoV2.getOilType());
        carParams.setFillingVolume(responseDtoV2.getOilQuantity());
        carParams.setServiceCharge(responseDtoV2.getServiceChargeBigDecimal());
        carParams.setIsActually(true);

        List<String> filters = responseDtoV2.getFilters();
        if (filters != null) {
            carParams.setOilFilter(filters.contains("fil1"));
            carParams.setFuelFilter(filters.contains("fil2"));
            carParams.setAirFilter(filters.contains("fil3"));
            carParams.setSalonFilter(filters.contains("fil4"));
            carParams.setPampersFilter(filters.contains("fil5"));
        }

        carParamsRepository.save(carParams);

        paymentService.set_data_payment(carParams.getId(), responseDtoV2.getServiceChargeBigDecimal(), responseDtoV2.getPayedChargeBigDecimal());
    }

    @Override
    public Page<CarParams> getPaginatedData(int page, int size, String param, Jwt jwt) {
        var master_user_id = jwt.getSubject();

        Specification<CarParams> specification_v1 = (root, query, criteriaBuilder) -> {
            Fetch<CarParams, Payment> fetch1 = root.fetch("payments", JoinType.LEFT);
            Fetch<CarParams, ClientCars> fetch2 = root.fetch("clientCars", JoinType.LEFT);
            Fetch<ClientCars, Users> fetch3 = fetch2.fetch("users", JoinType.LEFT);
            Fetch<Users, Users> fetch4 = fetch3.fetch("userChild", JoinType.LEFT);
            fetch3.fetch("roles", JoinType.LEFT);


            Join<ClientCars, Users> join3 = (Join<ClientCars, Users>) fetch3;
            Join<Users, Users> join4 = (Join<Users, Users>) fetch4;

            Predicate predicate1 = criteriaBuilder.equal(join4.get("id"), master_user_id);

            Predicate predicate2 = criteriaBuilder.like(criteriaBuilder.lower(join3.get("fullName")), "%" + param.toLowerCase() + "%");
            Predicate predicate3 = criteriaBuilder.like(criteriaBuilder.lower(join3.get("phone")), "%" + param.toLowerCase() + "%");
            return criteriaBuilder.and(predicate1, criteriaBuilder.or(predicate2, predicate3));
        };

        Pageable pageable = PageRequest.of(page, size);

        Page<CarParams> carParams = carParamsRepository.findAll(specification_v1, pageable);
        carParams.getContent().stream().forEach(carParams1 -> {
            carParams1.getClientCars().setCarParams(null);
        });
        return carParams;
    }
}
