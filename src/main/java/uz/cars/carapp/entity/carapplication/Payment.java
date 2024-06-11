package uz.cars.carapp.entity.carapplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payment", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_params_id", insertable = false, updatable = false)
    @JsonIgnore
    private CarParams carParams;

    @Column(name = "car_params_id", length = 50)
    private String carParamsId;

    @CreatedDate
    @Column(name = "ins_time", columnDefinition = " timestamp default current_timestamp", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insTime = new Date(System.currentTimeMillis());

    @Column(name = "money_give")
    private BigDecimal moneyGive; // shuncha to'ladi

    @Column(name = "money_need")
    private BigDecimal moneyNeed; // qolgan summa

    @Column(name = "excess_money")
    private BigDecimal excessMoney; // ortiqcha bergan


}
