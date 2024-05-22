package uz.cars.carapp.service.carapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cars.carapp.entity.carapplication.CarParams;
import uz.cars.carapp.entity.carapplication.ClientCars;
import uz.cars.carapp.repository.carapplication.CarParamsRepository;
import uz.cars.carapp.repository.carapplication.ClientCarsRepository;
import uz.cars.carapp.service.helperClasses.ResponseDto_v2;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarParamsService implements CarParamsInt{
    private final ClientCarsRepository clientCarsRepository;
    private final CarParamsRepository carParamsRepository;

    @Override
    public void save_car_params_by_user_id(ResponseDto_v2 responseDtoV2) {
        CarParams carParams = new CarParams();
        carParams.setClientCars(clientCarsRepository.findById(responseDtoV2.getCarId()).orElse(new ClientCars()));

        carParams.setMileage(Double.parseDouble(responseDtoV2.getCurrentDistance()));
        carParams.setToMileage(Double.parseDouble(responseDtoV2.getCurrentDistance()));
        carParams.setOilModel(responseDtoV2.getOilType());
        carParams.setFillingVolume(responseDtoV2.getOilQuantity());

        List<String> filters = responseDtoV2.getFilters();
        if (filters != null) {
            carParams.setOilFilter(filters.contains("fil1"));
            carParams.setFuelFilter(filters.contains("fil2"));
            carParams.setAirFilter(filters.contains("fil3"));
            carParams.setSalonFilter(filters.contains("fil4"));
            carParams.setPampersFilter(filters.contains("fil5"));
        }

        carParamsRepository.save(carParams);
    }
}
