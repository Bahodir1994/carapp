package uz.cars.carapp.entity.carapplication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import uz.cars.carapp.entity.authorization.Users;

import java.util.Date;

@Entity
@Table(name = "message_permissions", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessagePermissions {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", insertable = false, updatable = false)
    private MessageUsers messageUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private Users users;

    @CreatedDate
    @Column(name = "read_time", columnDefinition = " timestamp default current_timestamp", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date readTime = new Date(System.currentTimeMillis());
}
