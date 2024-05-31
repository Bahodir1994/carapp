package uz.cars.carapp.service.carapplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.cars.carapp.entity.authorization.Users;
import uz.cars.carapp.entity.carapplication.MessagePermissions;
import uz.cars.carapp.entity.carapplication.MessageUsers;
import uz.cars.carapp.repository.carapplication.MessagePermissionsRepository;
import uz.cars.carapp.repository.carapplication.MessageUsersRepository;
import uz.cars.carapp.service.helperClasses.ResponseDto_v3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements MessageServiceInt {
    private final MessagePermissionsRepository messagePermissionsRepository;
    private final MessageUsersRepository messageUsersRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<MessagePermissions> list_messages_sent_to_client(String id) {
        Specification<MessagePermissions> specification1 = (root, query, criteriaBuilder) -> {
            root.fetch("messageUsers", JoinType.LEFT);
            Fetch<MessagePermissions, Users> fetch1 = root.fetch("users", JoinType.LEFT);
            Join<MessagePermissions, Users> join1 = (Join<MessagePermissions, Users>) fetch1;

            Order order = criteriaBuilder.desc(root.get("readTime"));
            query.orderBy(order);
            return criteriaBuilder.equal(join1.get("id"), id);
        };

        return messagePermissionsRepository.findAll(specification1);
    }

    @Override
    @Transactional
    public void send_message_to_client(String clientId, String messageText, Jwt jwt) {
        var master_user_id = jwt.getSubject();

        MessageUsers messageUsers = new MessageUsers();
        messageUsers.setMessage(messageText);
        messageUsers.setInsTime(new Date());
        messageUsers.setUserId(master_user_id);

        messageUsersRepository.save(messageUsers);

        /**********************/

//        List<MessagePermissions> messagePermissions = new ArrayList<>();
//        for (ResponseDto_v3 s: responseDtoV3){
            MessagePermissions messagePermissionsOne = new MessagePermissions();
            messagePermissionsOne.setMessageId(messageUsers.getId());
            messagePermissionsOne.setUserId(clientId);
//            messagePermissions.add(messagePermissionsOne);
//        }

//        messagePermissionsRepository.saveAll(messagePermissions);
        messagePermissionsRepository.save(messagePermissionsOne);
    }
}
