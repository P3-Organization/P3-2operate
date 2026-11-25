package dk.aau.sw3.p32operate.business.mappers;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IConsumptionMapper;
import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
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
        assertThat(consumption.getDate()).isEqualTo(entity.getDate());
        assertThat(consumption.getKr()).isEqualTo(entity.getKr());
        assertThat(consumption.getKwh()).isEqualTo(entity.getKwh());
        assertThat(consumption.getMin_kr()).isEqualTo(entity.getMin_kr_total());

    }

    @Test
    public void testThatConsumptionEntityListIsMappedCorrectly(){
        ConsumptionEntity entity1 = new ConsumptionEntity(
                LocalDateTime.of(2026,10,10,0,0,0),
                170.4,
                80.4,
                60.4
        );
        ConsumptionEntity entity2 = new ConsumptionEntity(
                LocalDateTime.of(2026,11,10,0,0,0),
                140.4,
                81.4,
                43.4
        );

        List<ConsumptionEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);
        List<Consumption>  consumptions = mapper.mapToConsumptionList(entities);
        assertThat(consumptions).hasSize(2);

        assertThat(consumptions.getFirst().getDate()).isEqualTo(entity1.getDate());
        assertThat(consumptions.getFirst().getKr()).isEqualTo(entity1.getKr());
        assertThat(consumptions.getFirst().getKwh()).isEqualTo(entity1.getKwh());
        assertThat(consumptions.getFirst().getMin_kr()).isEqualTo(entity1.getMin_kr_total());

        assertThat(consumptions.get(1).getDate()).isEqualTo(entity2.getDate());
        assertThat(consumptions.get(1).getKr()).isEqualTo(entity2.getKr());
        assertThat(consumptions.get(1).getKwh()).isEqualTo(entity2.getKwh());
        assertThat(consumptions.get(1).getMin_kr()).isEqualTo(entity2.getMin_kr_total());
    }

}
