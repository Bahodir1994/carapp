package uz.cars.carapp.entity.carapplication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import uz.cars.carapp.entity.authorization.Users;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "client_cars", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientCars {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private Users users;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<CarParams> carParams;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "created_date", columnDefinition = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "car_model_code", length = 3)
    private String carModelCode;

    @Column(name = "car_color")
    private String carColor;
}
