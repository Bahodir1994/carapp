package uz.cars.carapp.entity.authorization;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "users_log", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersLog {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @CreatedDate
    @Column(name = "login_time", columnDefinition = " timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime = new Date(System.currentTimeMillis());

    @Column(name = "logout_time", columnDefinition = " timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTime = new Date(System.currentTimeMillis());

    @Column(name = "user_id", columnDefinition = "VARCHAR(50)")
    private String userId;

    @Column(name = "ip_address", columnDefinition = "VARCHAR(180)")
    private String ipAddress;
}

