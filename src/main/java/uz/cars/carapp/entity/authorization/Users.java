package uz.cars.carapp.entity.authorization;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "users", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @Column(name = "full_name", unique = true)
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "pin", length = 14, unique = true)
    private String pin;

    @Column(name = "phone", length = 20, unique = true)
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @OrderBy("id ASC")
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;
}
