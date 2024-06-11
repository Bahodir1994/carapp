package uz.cars.carapp.repository.carapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cars.carapp.entity.carapplication.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

}
