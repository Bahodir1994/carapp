package uz.cars.carapp.service.carapplication;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import uz.cars.carapp.entity.authorization.Users;
import uz.cars.carapp.entity.carapplication.ClientCars;
import uz.cars.carapp.repository.carapplication.CarsDataRepository;
import uz.cars.carapp.repository.carapplication.ClientCarsRepository;

@Service
@RequiredArgsConstructor
public class CarsService implements CarServiceInt {
    private final ClientCarsRepository clientCarsRepository;
    private final CarsDataRepository carsDataRepository;

    @Override
    public Page<ClientCars> cars_list(int page, int size, String param, Jwt jwt) {
        var master_user_id = jwt.getSubject();
        Specification<ClientCars> specification_user_rsh = (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class) {
                Fetch<ClientCars, Users> fetch1 = root.fetch("users", JoinType.LEFT);
                root.fetch("carParams", JoinType.LEFT);
                fetch1.fetch("userChild", JoinType.LEFT);
            }
            Join<ClientCars, Users> userChildJoin = root.join("users").join("userChild", JoinType.LEFT);


            Predicate predicate0 = criteriaBuilder.like(root.get("carNumber"), '%'+param.toUpperCase()+'%');
            Predicate predicate1 = criteriaBuilder.equal(userChildJoin.get("id"), master_user_id);
            if (param.equals("")){
                return predicate1;
            }
            return criteriaBuilder.and(predicate0, predicate1);
        };

        Pageable pageable = PageRequest.of(page, size);
        return clientCarsRepository.findAll(specification_user_rsh, pageable);
    }

    @Override
    public DataTablesOutput<ClientCars> cars_data_list(DataTablesInput dataTablesInput, String param) {

        return carsDataRepository.findAll(
                dataTablesInput,
                null,
                (root, query, criteriaBuilder) -> {
                    if (query.getResultType() != Long.class) {
                        root.fetch("users", JoinType.LEFT);
                        root.fetch("carParams", JoinType.LEFT);
                    }
                    if (param == null || param.equals("")){
                        return null;
                    }
                    Predicate predicate1 = criteriaBuilder.like(root.get("carNumber"), '%'+param.toUpperCase()+'%');
                    return criteriaBuilder.and(predicate1);
                }
        );
    }
}
