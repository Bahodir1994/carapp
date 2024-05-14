package uz.cars.carapp.controller.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cars.carapp.service.carapplication.CarsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/list_cars")
@RequiredArgsConstructor
public class Controller_GetData_Dashboard_v1 {
    private final CarsService carsService;

    @GetMapping
    @PreAuthorize("hasRole('user-master')")
    public ResponseEntity<Object> get_data_v1(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Object searchparam,
            DataTablesInput dataTableInput
    ) {
        dataTableInput.setLength(size);
        dataTableInput.setStart(page);
        if (searchparam == null){
            searchparam = "";
        }
        return ResponseEntity.ok().body(carsService.cars_list(page, size, String.valueOf(searchparam)));
    }
}
