package uz.cars.carapp.entity.authorization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import uz.cars.carapp.entity.carapplication.ClientCars;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "car_block")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(50)")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "pin", length = 14)
    private String pin;

    @Column(name = "phone", length = 20, unique = true)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    @JoinTable(
            name = "user_roles", schema = "car_block",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_relationships", schema = "car_block",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "master_id")
    )
    @JsonIgnore
    private Set<Users> userChild;


    public void addUserChild(Users child) {
        if (this.userChild == null) {
            this.userChild = new HashSet<>();
        }
        this.userChild.add(child);
    }

    public void removeUserChild(Users child) {
        if (this.userChild != null) {
            this.userChild.remove(child);
        }
    }
}
