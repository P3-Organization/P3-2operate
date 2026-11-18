package dk.aau.sw3.p32operate.presentation.mappers;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IConsumptionMapper;
import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import dk.aau.sw3.p32operate.presentation.dto.ConsumptionDTO;
import dk.aau.sw3.p32operate.presentation.mappers.interfaces.IConsumptionDTOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConsumptionDTOMapperTest {
    @Autowired
    private IConsumptionDTOMapper dtoMapper;

    @Test
    public void testThatConsumptionIsMappedCorrectlyToDTO(){
        Consumption consumption = new Consumption(
                LocalDateTime.of(2026,10,10,0,0,0),
                170.4,
                80.4,
                60.4
        );
        ConsumptionDTO consumptionDTO = dtoMapper.mapToDTO(consumption);
        assertThat(consumption.getDate()).isEqualTo(consumptionDTO.date());
        assertThat(consumption.getKr()).isEqualTo(consumptionDTO.kr());
        assertThat(consumption.getKwh()).isEqualTo(consumptionDTO.kwh());
        assertThat(consumption.getMin_kr()).isEqualTo(consumptionDTO.min_kr());

    }

    @Test
    public void testThatConsumptionListIsMappedCorrectlyToDTO(){
        Consumption consumption1 = new Consumption(
                LocalDateTime.of(2026,10,10,0,0,0),
                170.4,
                80.4,
                60.4
        );
        Consumption consumption2 = new Consumption(
                LocalDateTime.of(2026,11,10,0,0,0),
                140.4,
                81.4,
                43.4
        );

        List<Consumption> consumptions = new ArrayList<>();
        consumptions.add(consumption1);
        consumptions.add(consumption2);
        List<ConsumptionDTO>  consumptionDTOS = dtoMapper.mapToDTOList(consumptions);
        assertThat(consumptionDTOS).hasSize(2);

        assertThat(consumptionDTOS.getFirst().date()).isEqualTo(consumption1.getDate());
        assertThat(consumptionDTOS.getFirst().kr()).isEqualTo(consumption1.getKr());
        assertThat(consumptionDTOS.getFirst().kwh()).isEqualTo(consumption1.getKwh());
        assertThat(consumptionDTOS.getFirst().min_kr()).isEqualTo(consumption1.getMin_kr());

        assertThat(consumptionDTOS.get(1).date()).isEqualTo(consumption2.getDate());
        assertThat(consumptionDTOS.get(1).kr()).isEqualTo(consumption2.getKr());
        assertThat(consumptionDTOS.get(1).kwh()).isEqualTo(consumption2.getKwh());
        assertThat(consumptionDTOS.get(1).min_kr()).isEqualTo(consumption2.getMin_kr());
    }
}
