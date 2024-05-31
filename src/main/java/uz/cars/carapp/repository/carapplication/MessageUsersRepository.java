package uz.cars.carapp.repository.carapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cars.carapp.entity.carapplication.MessageUsers;

@Repository
public interface MessageUsersRepository extends JpaRepository<MessageUsers, String> {
}