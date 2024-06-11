package uz.cars.carapp.service.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cars.carapp.entity.carapplication.Payment;
import uz.cars.carapp.repository.carapplication.PaymentRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentInt {
    private final PaymentRepository paymentRepository;


    @Override
    public void set_data_payment(String carParamId, BigDecimal serviceCharge, BigDecimal paymentGive) {
        Payment payment = new Payment();
        BigDecimal result1 = serviceCharge.subtract(paymentGive);

        if (result1.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal mainExcess = payment.getExcessMoney().add(result1.abs());
            payment.setExcessMoney(mainExcess);
            result1 = BigDecimal.ZERO;
        }

        payment.setCarParamsId(carParamId);
        payment.setMoneyGive(paymentGive);
        payment.setMoneyNeed(result1);
        paymentRepository.save(payment);
    }

    @Override
    public void update_data_payment(String id, BigDecimal pay, Boolean isDebt) {
        paymentRepository.findById(id).ifPresent(payment -> {
            if (isDebt){
                BigDecimal mainDebt = payment.getMoneyNeed().add(pay);
                payment.setMoneyNeed(mainDebt);
            }else {
                BigDecimal mainDebt = payment.getMoneyNeed().subtract(pay);

                if (mainDebt.compareTo(BigDecimal.ZERO) < 0) {
                    BigDecimal mainExcess = payment.getExcessMoney().add(mainDebt.abs());
                    payment.setExcessMoney(mainExcess);
                    mainDebt = BigDecimal.ZERO;
                }

                payment.setMoneyNeed(mainDebt);
            }
        });
    }
}
