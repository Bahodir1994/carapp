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
import uz.cars.carapp.repository.authorization.UsersRepository;
import uz.cars.carapp.repository.carapplication.ClientCarsRepository;
import uz.cars.carapp.service.helperClasses.ResponseDto_v1;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientServiceInt {

    private final UsersRepository usersRepository;
    private final ClientCarsRepository clientCarsRepository;

    @Override
    public Page<Users> master_clients_list(int page, int size, String param, Jwt jwt) {
        var master_user_id = jwt.getSubject();

        Specification<Users> specification_v1 = (root, query, criteriaBuilder) -> {
            Fetch<Users, Users> fetch1 = root.fetch("userChild", JoinType.LEFT);
            Fetch<Users, Roles> fetch2 = root.fetch("roles", JoinType.LEFT);
            Join<Users, Users> join1 = (Join<Users, Users>) fetch1;
            Predicate predicate1 = criteriaBuilder.equal(join1.get("id"), master_user_id);
            Predicate predicate2 = criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + param.toLowerCase() + "%");
            Predicate predicate3 = criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + param.toLowerCase() + "%");
            return criteriaBuilder.and(predicate1, criteriaBuilder.or(predicate2, predicate3));
        };

        Pageable pageable = PageRequest.of(page, size);
        return usersRepository.findAll(specification_v1, pageable);
    }

    @Override
    public List<Users> master_clients_list_no_page(String param, Jwt jwt) {
        var master_user_id = jwt.getSubject();

        Specification<Users> specification_v1 = (root, query, criteriaBuilder) -> {
            Fetch<Users, Users> fetch1 = root.fetch("userChild", JoinType.LEFT);
            Fetch<Users, Roles> fetch2 = root.fetch("roles", JoinType.LEFT);
            Join<Users, Users> join1 = (Join<Users, Users>) fetch1;
            Predicate predicate1 = criteriaBuilder.equal(join1.get("id"), master_user_id);
            Predicate predicate2 = criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + param.toLowerCase() + "%");
            Predicate predicate3 = criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + param.toLowerCase() + "%");
            return criteriaBuilder.and(predicate1, criteriaBuilder.or(predicate2, predicate3));
        };

        return usersRepository.findAll(specification_v1);
    }

    @Override
    public void save_master_clients(ResponseDto_v1 responseDtoV1, Jwt jwt) throws Throwable {
        var master_user_id = jwt.getSubject();
        Optional<Users> masterUser = usersRepository.findById(master_user_id);

        Users users = new Users();
        if (masterUser.isPresent()){
            users.setPin("");
            users.setPhone(responseDtoV1.getPhone());
            users.setEmail("");
            users.setUsername(responseDtoV1.getFio().substring(0, 3) + (responseDtoV1.getPhone().length() >= 3 ? responseDtoV1.getPhone().substring(responseDtoV1.getPhone().length() - 3) : responseDtoV1.getPhone()));
            users.setFullName(responseDtoV1.getFio());
            users.addUserChild(masterUser.get());
            usersRepository.save(users);
        } else {
            throw new Throwable();
        }

        save_client_cars(responseDtoV1.getCarType(), responseDtoV1.getCarNumber(), responseDtoV1.getCarColor(), users);
    }

    @Override
    public void save_client_cars(String carType, String carNumber, String carColor, Users users) {
        ClientCars clientCars = new ClientCars();
        clientCars.setCarNumber(carNumber);
        clientCars.setCarColor(carColor);
        clientCars.setCarModelCode(carType);
        clientCars.setUsers(users);
        clientCarsRepository.save(clientCars);
    }

    @Override
    public List<ClientCars> get_car_detail_by_client_id(String id) {
        Specification<ClientCars> specification = (root, query, criteriaBuilder) -> {
            Fetch<ClientCars, Users> fetch1 = root.fetch("users", JoinType.LEFT);
            Fetch<Users, Roles> fetch2 = fetch1.fetch("roles", JoinType.LEFT);
            Fetch<ClientCars, CarParams> fetch3 = root.fetch("carParams", JoinType.LEFT);
            Fetch<CarParams, Payment> fetch4 = fetch3.fetch("payments", JoinType.LEFT);

            Join<ClientCars, Users> join1 = (Join<ClientCars, Users>) fetch1;

            Predicate predicate1 = criteriaBuilder.equal(join1.get("id"), id);
            return criteriaBuilder.and(predicate1);
        };

        return clientCarsRepository.findAll(specification);
    }
}
