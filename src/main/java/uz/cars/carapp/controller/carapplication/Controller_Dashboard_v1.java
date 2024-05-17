package uz.cars.carapp.controller.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import uz.cars.carapp.service.carapplication.CarsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class Controller_Dashboard_v1 {
    private final CarsService carsService;

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
        return ResponseEntity.ok().body(carsService.cars_list(page, size, String.valueOf(searchparam), jwt));
    }


    @GetMapping("v2")
    @PreAuthorize("hasRole('master')  or hasRole('admin')")
    public ResponseEntity<Object> get_data_v2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Object searchparam,
            @AuthenticationPrincipal Jwt jwt
            ) {
        if (searchparam == null){
            searchparam = "";
        }
        return ResponseEntity.ok().body(carsService.cars_list(page, size, String.valueOf(searchparam), jwt));
    }
}
