package uz.cars.carapp.repository.authorization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cars.carapp.entity.authorization.Users;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Page<Users> findAll(Specification<Users> specificationUserRsh, Pageable pageable);

    Users findAll(Specification<Users> specificationUserRsh);
}
