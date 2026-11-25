package dk.aau.sw3.p32operate.presentation.mappers.interfaces;

import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.presentation.dto.ConsumptionDTO;

import java.util.List;

public interface IConsumptionDTOMapper {
    ConsumptionDTO mapToDTO(Consumption consumption);
    List<ConsumptionDTO> mapToDTOList(List<Consumption> consumptions);
}
