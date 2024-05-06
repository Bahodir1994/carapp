package uz.cars.carapp.service.carapplication;

import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.cars.carapp.entity.carapplication.ClientCars;
import uz.cars.carapp.repository.carapplication.CarsDataRepository;
import uz.cars.carapp.repository.carapplication.CarsRepository;

@Service
@RequiredArgsConstructor
public class CarsService implements CarServiceInt {
    private final CarsRepository carsRepository;
    private final CarsDataRepository carsDataRepository;

    @Override
    public Page<ClientCars> cars_list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<ClientCars> specification = (root, query, criteriaBuilder) -> {
            root.fetch("users", JoinType.LEFT);
            root.fetch("carParams", JoinType.LEFT);

            return null;
        };

        return carsRepository.findAll(specification, pageable);
    }

    @Override
    public DataTablesOutput<ClientCars> cars_data_list(DataTablesInput dataTablesInput) {
        Specification<ClientCars> specification = (root, query, criteriaBuilder) -> {
            root.fetch("users", JoinType.LEFT);
            root.fetch("carParams", JoinType.LEFT);

            return null;
        };

        return carsDataRepository.findAll(
                dataTablesInput,
                null,
                (root, query, criteriaBuilder) -> {
                    if (query.getResultType() != Long.class) {
                        root.fetch("users", JoinType.LEFT);
                        root.fetch("carParams", JoinType.LEFT);
                    }
                    return null;
                }
        );
    }
}
