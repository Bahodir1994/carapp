package uz.cars.carapp.controller.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import uz.cars.carapp.service.carapplication.CarParamsService;
import uz.cars.carapp.service.carapplication.ClientService;
import uz.cars.carapp.service.carapplication.PaymentService;
import uz.cars.carapp.service.helperClasses.ResponseDto_v1;
import uz.cars.carapp.service.helperClasses.ResponseDto_v4;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class Controller_payment_v1 {
    private final CarParamsService carParamsService;
    private final PaymentService paymentService;

    @GetMapping
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> get_data_v1(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) int draw,
            @RequestParam(required = false) Object searchparam,
            @AuthenticationPrincipal Jwt jwt

    ) {
        if (searchparam == null){
            searchparam = "";
        }
        return ResponseEntity.ok().body(carParamsService.getPaginatedData(page, size, String.valueOf(searchparam), jwt));
    }

//    @GetMapping("/{draw}/{id}")
//    @PreAuthorize("hasRole('master')")
//    public ResponseEntity<Object> get_data_v2(
//            @PathVariable(name = "draw") int draw,
//            @PathVariable(name = "id") String id
//    ){
//        return ResponseEntity.ok().body(paymentService.set_data_payment(id));
//    }

    @PostMapping
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> post_data_v1(
            @RequestBody ResponseDto_v4 responseDto_v4,
            @AuthenticationPrincipal Jwt jwt
    ) {
        paymentService.update_data_payment(responseDto_v4.getId(), responseDto_v4.getPayBigDecimal(), responseDto_v4.getIsDebt());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
