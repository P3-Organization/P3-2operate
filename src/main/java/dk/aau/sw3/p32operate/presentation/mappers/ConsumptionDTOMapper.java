package dk.aau.sw3.p32operate.presentation.mappers;

import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import dk.aau.sw3.p32operate.presentation.dto.ConsumptionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumptionDTOMapper {
    public ConsumptionDTO mapToDTO(Consumption consumption){
        return new ConsumptionDTO(consumption.getDate(), consumption.getKwh(), consumption.getKr(), consumption.getMin_kr());
    }

    public List<ConsumptionDTO> mapToDTOList(List<Consumption> consumptions){
        List<ConsumptionDTO> consumptionDTOS = new ArrayList<>();
        for(Consumption c : consumptions){
            consumptionDTOS.add(mapToDTO(c));
        }
        return consumptionDTOS;
    }
}
