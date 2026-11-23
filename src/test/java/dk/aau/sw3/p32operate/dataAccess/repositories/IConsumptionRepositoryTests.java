package dk.aau.sw3.p32operate.dataAccess.repositories;

import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false"
})
public class IConsumptionRepositoryTests {
    @Autowired
    private IConsumptionRepository consumptionRepository;

    private ConsumptionEntity testConsumption1;
    private ConsumptionEntity testConsumption2;
    private ConsumptionEntity testConsumption3;
    @BeforeEach
    public void setUp(){
        testConsumption1 = new ConsumptionEntity();
        testConsumption1.setKr(77.0);
        testConsumption1.setKwh(138.4);
        testConsumption1.setMin_kr_total(60.0);
        consumptionRepository.save(testConsumption1);

        testConsumption2 = new ConsumptionEntity();
        testConsumption2.setKr(100.0);
        testConsumption2.setKwh(200.4);
        testConsumption2.setMin_kr_total(80.0);
        testConsumption2.setDate(LocalDateTime.of(2026,10,10,0,0,0));
        consumptionRepository.save(testConsumption2);

        testConsumption3 = new ConsumptionEntity();
        testConsumption3.setKr(100.0);
        testConsumption3.setKwh(200.4);
        testConsumption3.setMin_kr_total(80.0);
        testConsumption3.setDate(LocalDateTime.of(2026,11,10,0,0,0));
        consumptionRepository.save(testConsumption3);
    }

    @Test
    void testThatInsertedConsumptionCanBeFound(){
        Optional<ConsumptionEntity> consumption = consumptionRepository.findByDate(testConsumption1.getDate());
        assertThat(consumption).isPresent();
        assertThat(consumption.get()).isEqualTo(testConsumption1);
    }

    @Test
    void testThatAllInsertedConsumptionsCanBeFound(){
        List<ConsumptionEntity> entities = consumptionRepository.findAll();
        assertThat(entities).hasSize(3);
        assertThat(entities).contains(testConsumption1, testConsumption2, testConsumption3);
    }

    @Test
    void testThatNonExistingEntityReturnEmpty(){
        Optional<ConsumptionEntity> entity = consumptionRepository.findByDate(LocalDateTime.now());
        assertThat(entity).isEmpty();
    }

    @Test
    void testThatConsumptionCanBeDeleted(){
        Optional<ConsumptionEntity> entity = consumptionRepository.findByDate(testConsumption1.getDate());
        assertThat(entity).isPresent();
        consumptionRepository.delete(entity.get());
        entity = consumptionRepository.findByDate(testConsumption1.getDate());
        assertThat(entity).isEmpty();
    }

}
