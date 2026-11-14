package dk.aau.sw3.p32operate.dataAccess.repositories;


import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false"
})
public class IRoomRecordRepositoryTest {
    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IRoomRecordRepository recordRepository;

    @Autowired
    private TestEntityManager entityManager;

    RoomEntity testRoom1;

    RoomRecordEntity record1;
    RoomRecordEntity record2;
    RoomRecordEntity record3;

    @BeforeEach
    public void setUp(){
        testRoom1 = new RoomEntity();
        testRoom1.setRoomId(1);
        testRoom1.setRoomName("Test room 1");
        roomRepository.save(testRoom1);

        record1 = new RoomRecordEntity();
        record1.setRoom(testRoom1);
        record1.setTime(LocalDateTime.of(2026,10,10,2,0,0));
        record1.setHumidity(28.1);
        record1.setHoaHumidity(null);
        record1.setTemperature(39.5);
        record1.setHoaTemperature(null);
        record1.setSignalTemperature(null);
        recordRepository.save(record1);

        record2 = new RoomRecordEntity();
        record2.setRoom(testRoom1);
        record2.setTime(LocalDateTime.of(2026,11,10,2,0,0));
        record2.setHumidity(40.1);
        record2.setHoaHumidity(21.4);
        record2.setTemperature(31.2);
        record2.setHoaTemperature(44.2);
        record2.setSignalTemperature(17.5);
        recordRepository.save(record2);

        record3 = new RoomRecordEntity();
        record3.setRoom(testRoom1);
        record3.setTime(LocalDateTime.of(2026,11,10,2,0,0));
        record3.setHumidity(20.1);
        record3.setHoaHumidity(null);
        record3.setTemperature(38.4);
        record3.setHoaTemperature(32.1);
        record3.setSignalTemperature(11.5);
        recordRepository.save(record3);

    }

    @Test
    void testThatInsertedRecordCanBeFound(){
        Optional<RoomRecordEntity> record = recordRepository.findById(record1.getId());
        assertThat(record).isPresent();
        assertThat(record.get()).isEqualTo(record1);
    }

    @Test
    void testThatAllInsertedRoomsCanBeFound(){
        List<RoomRecordEntity> entities = recordRepository.findAll();
        assertThat(entities).hasSize(3);
        assertThat(entities).contains(record1, record2, record3);
    }

    @Test
    void testThatNonExistingEntityReturnEmpty(){
        Optional<RoomRecordEntity> entity = recordRepository.findById(5);
        assertThat(entity).isEmpty();
    }

    @Test
    void testThatRoomCanBeFound(){
        Optional<RoomRecordEntity> entity = recordRepository.findById(record1.getId());
        assertThat(entity).isPresent();
        assertThat(entity.get().getRoom()).isEqualTo(testRoom1);
    }

    @Test
    void testThatRecordCanBeDeleted(){
        Optional<RoomRecordEntity> entity = recordRepository.findById(record2.getId());
        assertThat(entity).isPresent();
        recordRepository.delete(record2);
        entity = recordRepository.findById(record2.getId());
        assertThat(entity).isEmpty();
    }


    @AfterEach
    public void tearDown(){
        roomRepository.delete(testRoom1);

        recordRepository.delete(record1);
        recordRepository.delete(record2);
        recordRepository.delete(record3);
    }
}
