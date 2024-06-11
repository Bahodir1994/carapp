package uz.cars.carapp.entity.catalog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oil_weight", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OilWeight {
    @Id
    @Column(name = "code", length = 3)
    private String code;

    @Column(name = "oil_amount")
    private Double oilAmount;
}
