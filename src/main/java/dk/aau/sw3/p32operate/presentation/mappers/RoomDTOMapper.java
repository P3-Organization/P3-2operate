package dk.aau.sw3.p32operate.presentation.mappers;

import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import dk.aau.sw3.p32operate.presentation.dto.RoomDTO;
import dk.aau.sw3.p32operate.presentation.dto.RoomRecordDTO;
import dk.aau.sw3.p32operate.presentation.mappers.interfaces.IRoomDTOMapper;
import dk.aau.sw3.p32operate.presentation.mappers.interfaces.IRoomRecordDTOMapper;

import java.util.ArrayList;
import java.util.List;

public class RoomDTOMapper implements IRoomDTOMapper {
    IRoomRecordDTOMapper recordDTOMapper;

    public RoomDTOMapper(IRoomRecordDTOMapper mapper){
        this.recordDTOMapper = mapper;
    }

    @Override
    public Room mapToRoomDTO(Room room){
        List<RoomRecordDTO> records = new ArrayList<>();
        RoomDTO roomDTO = new RoomDTO(room.getRoomId(),room.getRoomName(),records);
        for(RoomRecord re : room.getRecords()){
            records.add(recordDTOMapper.mapToRoomRecordDTO(re, roomDTO));
        }
        return room;
    }
}
