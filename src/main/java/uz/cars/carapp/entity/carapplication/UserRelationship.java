package uz.cars.carapp.entity.carapplication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import uz.cars.carapp.entity.authorization.Users;

@Entity
@Table(name = "user_relationships", schema = "car_block")
@Getter
@Setter
public class UserRelationship {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "relationship_id", columnDefinition = "VARCHAR(50)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Users master;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Users client;
}