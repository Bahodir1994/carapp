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
@Table(name = "oil_type", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OilType {
    @Id
    @Column(name = "code", length = 3)
    private String code;

    @Column(name = "model")
    private String model;

    @Column(name = "name")
    private String name;
}
