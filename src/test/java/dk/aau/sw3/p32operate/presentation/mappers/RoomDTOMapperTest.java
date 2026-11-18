package dk.aau.sw3.p32operate.presentation.mappers;

import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.presentation.dto.RoomDTO;
import dk.aau.sw3.p32operate.presentation.dto.RoomRecordDTO;
import dk.aau.sw3.p32operate.presentation.mappers.interfaces.IRoomDTOMapper;
import dk.aau.sw3.p32operate.presentation.mappers.interfaces.IRoomRecordDTOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoomDTOMapperTest {
    @Autowired
    IRoomDTOMapper roomMapper;

    @Autowired
    IRoomRecordDTOMapper recordMapper;

    @Test
    public void testThatRoomIsMappedCorrectlyAndRoomRecordToDTO(){
        List<RoomRecord> roomRecords = new ArrayList<>();
        Room room = new Room();
        RoomRecord roomRecord = new RoomRecord(
                1,
                room,
                LocalDateTime.of(2026,11,11,0,0,0),
                20.5,
                null,
                46.4,
                19.2,
                17.4);

        roomRecords.add(roomRecord);

        room.setRoomName("Test room");
        room.setRoomId(1);
        room.setRecords(roomRecords);

        RoomDTO roomDTO = roomMapper.mapToRoomDTO(room);
        RoomRecordDTO roomRecordDTO = recordMapper.mapToRoomRecordDTO(roomRecord, roomDTO);
        assertThat(roomDTO.roomId()).isEqualTo(1);
        assertThat(roomDTO.roomName()).isEqualTo("Test room");
        assertThat(roomDTO.roomRecordDTOS().getFirst()).isEqualTo(roomRecordDTO);
        assertThat(roomDTO.roomRecordDTOS()).hasSize(1);
    }
}
