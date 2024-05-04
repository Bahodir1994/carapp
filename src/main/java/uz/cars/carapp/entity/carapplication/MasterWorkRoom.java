package uz.cars.carapp.entity.carapplication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import uz.cars.carapp.entity.authorization.Users;

@Entity
@Table(name = "master_work_room", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MasterWorkRoom {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private Users users;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "address", length = 600)
    private String address;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "working_hours")
    private String workingHours;

    @Column(name = "comment", length = 1200)
    private String comment;
}
