package dk.aau.sw3.p32operate.presentation.mappers.interfaces;

import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.presentation.dto.RoomDTO;
import dk.aau.sw3.p32operate.presentation.dto.RoomRecordDTO;

public interface IRoomRecordDTOMapper {
    RoomRecordDTO mapToRoomRecordDTO(RoomRecord roomRecord, RoomDTO roomDTO);
}
