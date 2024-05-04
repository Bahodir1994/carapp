package uz.cars.carapp.entity.catalog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catalog_cars", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CatalogCars {
    @Id
    @Column(name = "code", length = 3)
    private String code;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "brand_icon")
    private String brandIcon;
}
