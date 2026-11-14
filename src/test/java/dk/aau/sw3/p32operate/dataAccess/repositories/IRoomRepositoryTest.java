package dk.aau.sw3.p32operate.dataAccess.repositories;

import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false"
})
public class IRoomRepositoryTest {
    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IRoomRecordRepository recordRepository;

    RoomEntity testRoom1;
    RoomEntity testRoom2;
    RoomEntity testRoom3;

    RoomRecordEntity record1;
    RoomRecordEntity record2;

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

        testRoom2 = new RoomEntity();
        testRoom2.setRoomId(2);
        testRoom2.setRoomName("Test room 2");
        roomRepository.save(testRoom2);

        testRoom3 = new RoomEntity();
        testRoom3.setRoomId(3);
        testRoom3.setRoomName("Test room 3");
        roomRepository.save(testRoom3);


    }

    @Test
    void testThatInsertedRoomCanBeFound(){
        Optional<RoomEntity> room = roomRepository.findByRoomId(testRoom1.getRoomId());
        assertThat(room).isPresent();
        assertThat(room.get()).isEqualTo(testRoom1);
    }

    @Test
    void testThatAllInsertedRoomsCanBeFound(){
        List<RoomEntity> entities = roomRepository.findAll();
        assertThat(entities).hasSize(3);
        assertThat(entities).contains(testRoom1, testRoom2, testRoom3);
    }

    @Test
    void testThatNonExistingEntityReturnEmpty(){
        Optional<RoomEntity> entity = roomRepository.findByRoomId(5);
        assertThat(entity).isEmpty();
    }

    @Test
    void testThatRoomRecordsCanBeFound(){
        Optional<RoomEntity> entity = roomRepository.findByRoomId(1);
        assertThat(entity).isPresent();
        assertThat(entity.get().getRoomRecordings()).hasSize(2);
//        assertThat(entity.get().getRoomRecordings()).contains(record1, record2);
    }


    @AfterEach
    public void tearDown(){
        roomRepository.delete(testRoom1);
        roomRepository.delete(testRoom2);
        roomRepository.delete(testRoom3);
    }

}
