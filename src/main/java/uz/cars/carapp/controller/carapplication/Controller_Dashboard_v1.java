package uz.cars.carapp.controller.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import uz.cars.carapp.service.carapplication.CarParamsService;
import uz.cars.carapp.service.carapplication.CarsService;
import uz.cars.carapp.service.helperClasses.ResponseDto_v1;
import uz.cars.carapp.service.helperClasses.ResponseDto_v2;

@CrossOrigin("*")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class Controller_Dashboard_v1 {
    private final CarsService carsService;
    private final CarParamsService carParamsService;

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
        return ResponseEntity.ok().body(carsService.cars_list(page, size, String.valueOf(searchparam), jwt));
    }


    @GetMapping("v2")
    @PreAuthorize("hasRole('master')  or hasRole('admin')")
    public ResponseEntity<Object> get_data_v2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) int draw,
            @RequestParam(required = false) Object searchparam,
            @AuthenticationPrincipal Jwt jwt
            ) {
        if (searchparam == null){
            searchparam = "";
        }
        return ResponseEntity.ok().body(carsService.cars_list(page, size, String.valueOf(searchparam), jwt));
    }

    @PostMapping
    @PreAuthorize("hasRole('master')")
    public ResponseEntity<Object> post_data_v1(
            @RequestBody ResponseDto_v2 responseDtoV2,
            @AuthenticationPrincipal Jwt jwt
    ) {
        carParamsService.save_car_params_by_user_id(responseDtoV2);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
