package dk.aau.sw3.p32operate.presentation.mappers;

import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.presentation.dto.RoomDTO;
import dk.aau.sw3.p32operate.presentation.dto.RoomRecordDTO;
import dk.aau.sw3.p32operate.presentation.mappers.interfaces.IRoomRecordDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomRecordDTOMapper implements IRoomRecordDTOMapper {

    @Override
    public RoomRecordDTO mapToRoomRecordDTO(RoomRecord roomRecord, RoomDTO roomDTO){
        return new RoomRecordDTO(
                roomRecord.getId(),
                roomDTO,
                roomRecord.getTime(),
                roomRecord.getHumidity(),
                roomRecord.getHoaHumidity(),
                roomRecord.getTemperature(),
                roomRecord.getHoaTemperature(),
                roomRecord.getSignalTemperature()
        );
    }
}
