package uz.cars.carapp.controller.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import uz.cars.carapp.service.carapplication.ClientService;
import uz.cars.carapp.service.carapplication.MessageService;
import uz.cars.carapp.service.helperClasses.ResponseDto_v3;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class Controller_Messages_v1 {
    private final ClientService clientService;
    private final MessageService messageService;

    /*get clients by master*/
    @GetMapping
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> get_data_v1(
            @RequestParam(required = false) int draw,
            @RequestParam(required = false) Object searchparam,
            @AuthenticationPrincipal Jwt jwt
    ) {
        if (searchparam == null){
            searchparam = "";
        }
        return ResponseEntity.ok().body(clientService.master_clients_list_no_page(String.valueOf(searchparam), jwt));
    }

    /*get clients by master*/
    @GetMapping("/{draw}/{item_id}")
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> get_data_v2(
            @PathVariable(name = "draw", required = false) int draw,
            @PathVariable(name = "item_id", required = false) String searchparam,
            @AuthenticationPrincipal Jwt jwt
    ) {
        if (searchparam == null){
            searchparam = "";
        }
        return ResponseEntity.ok().body(messageService.list_messages_sent_to_client(String.valueOf(searchparam)));
    }


    /*set message by clientId and masterId*/
    @PostMapping
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> post_data_v1(
            @RequestBody ResponseDto_v3 responseDtoV3,
            @AuthenticationPrincipal Jwt jwt
    ){
        messageService.send_message_to_client(responseDtoV3.getClientId(), responseDtoV3.getMessageText(), jwt);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
