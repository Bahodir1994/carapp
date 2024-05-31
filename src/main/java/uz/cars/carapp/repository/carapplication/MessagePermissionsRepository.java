package uz.cars.carapp.repository.carapplication;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cars.carapp.entity.carapplication.MessagePermissions;

import java.util.List;

@Repository
public interface MessagePermissionsRepository extends JpaRepository<MessagePermissions, String> {

    List<MessagePermissions> findAll(Specification<MessagePermissions> specification1);
}
