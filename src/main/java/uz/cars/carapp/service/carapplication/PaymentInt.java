package uz.cars.carapp.service.carapplication;

import java.math.BigDecimal;

public interface PaymentInt {

    void set_data_payment(String carParamId, BigDecimal serviceCharge, BigDecimal paymentGive);

    // agar isDebt true bo'lsa paymentNeed ga qoshiladi bomasa ayriladi
    void update_data_payment(String id, BigDecimal pay, Boolean isDebt);
}
