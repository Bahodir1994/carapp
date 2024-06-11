package uz.cars.carapp.entity.carapplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "car_params", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarParams {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private ClientCars clientCars;

    @OneToMany(mappedBy = "carParams", fetch = FetchType.LAZY)
    private Set<Payment> payments;

    @CreatedDate
    @Column(name = "ins_time", columnDefinition = " timestamp default current_timestamp", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insTime = new Date(System.currentTimeMillis());

    @Column(name = "mileage")
    private double mileage; // Пробег текуший

    @Column(name = "to_mileage")
    private double toMileage; // Пробег до

    @Column(name = "oil_model")
    private String oilModel; // модель заливки

    @Column(name = "filling_volume")
    private String fillingVolume; // объем заливки

    @Column(name = "oil_filter")
    private Boolean oilFilter; // фильтр маслянный

    @Column(name = "fuel_filter")
    private Boolean fuelFilter; // фильтр топливо

    @Column(name = "air_filter")
    private Boolean airFilter; // фильтр воздушный

    @Column(name = "salon_filter")
    private Boolean salonFilter; // фильтр салонный

    @Column(name = "pampers_filter")
    private Boolean pampersFilter; // фильтр памперс

    @Column(name = "service_charge")
    private BigDecimal serviceCharge; // плата за обслуживание

    @Column(name = "is_actually")
    private Boolean isActually; // это последный статус
}
