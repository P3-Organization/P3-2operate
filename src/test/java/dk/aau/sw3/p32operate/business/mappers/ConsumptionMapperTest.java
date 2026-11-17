package dk.aau.sw3.p32operate.business.mappers;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IConsumptionMapper;
import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ConsumptionMapperTest {
    @Autowired
    private IConsumptionMapper mapper;

    @Test
    public void testThatEntityIsMappedCorrectly(){
         ConsumptionEntity entity = new ConsumptionEntity(
                LocalDateTime.of(2026,10,10,0,0,0),
                170.4,
                80.4,
                60.4
                );
        Consumption  consumption = mapper.mapToConsumption(entity);

    }
}
