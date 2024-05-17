package uz.cars.carapp.controller.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import uz.cars.carapp.service.carapplication.ClientService;
import uz.cars.carapp.service.helperClasses.ResponseDto_v1;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class Controller_Products_v1 {
    private final ClientService clientService;

    @GetMapping
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> get_data_v1(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Object searchparam,
            @AuthenticationPrincipal Jwt jwt
            ) {
        if (searchparam == null){
            searchparam = "";
        }
        return ResponseEntity.ok().body(clientService.master_clients_list(page, size, String.valueOf(searchparam), jwt));
    }


    @PostMapping
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> get_data_v1(
            @RequestBody ResponseDto_v1 responseDtoV1,
            @AuthenticationPrincipal Jwt jwt
    ) throws Throwable {
        clientService.save_master_clients(responseDtoV1, jwt);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
