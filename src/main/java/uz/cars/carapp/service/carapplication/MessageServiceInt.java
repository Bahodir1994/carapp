package uz.cars.carapp.service.carapplication;

import org.springframework.security.oauth2.jwt.Jwt;
import uz.cars.carapp.entity.carapplication.MessagePermissions;
import uz.cars.carapp.service.helperClasses.ResponseDto_v3;

import java.util.List;

public interface MessageServiceInt {
    List<MessagePermissions> list_messages_sent_to_client(String id);

    void send_message_to_client(String clientId, String messageText, Jwt jwt);
}
