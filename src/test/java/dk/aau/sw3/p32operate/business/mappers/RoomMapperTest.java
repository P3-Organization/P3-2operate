package dk.aau.sw3.p32operate.business.mappers;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IRoomMapper;
import static org.assertj.core.api.Assertions.assertThat;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IRoomRecordMapper;
import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RoomMapperTest {
    @Autowired
    IRoomMapper roomMapper;

    @Autowired
    IRoomRecordMapper recordMapper;

    @Test
    public void testThatRoomEntityIsMappedCorrectlyAndRoomRecord(){
        List<RoomRecordEntity> roomRecordEntities = new ArrayList<>();
        RoomEntity roomEntity = new RoomEntity();
        RoomRecordEntity roomRecordEntity = new RoomRecordEntity(
                1,
                roomEntity,
                LocalDateTime.of(2026,11,11,0,0,0),
                20.5,
                null,
                46.4,
                19.2,
                17.4);

        roomRecordEntities.add(roomRecordEntity);

        roomEntity.setRoomName("Test room");
        roomEntity.setRoomId(1);
        roomEntity.setRoomRecordings(roomRecordEntities);

        Room room = roomMapper.mapToRoom(roomEntity);
        RoomRecord roomRecord =recordMapper.mapToRoomRecord(roomRecordEntity, room);
        assertThat(room.getRoomId()).isEqualTo(1);
        assertThat(room.getRoomName()).isEqualTo("Test room");
        assertThat(room.getRecords().getFirst()).isEqualTo(roomRecord);
        assertThat(room.getRecords()).hasSize(1);
    }
}
