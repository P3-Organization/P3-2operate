package dk.aau.sw3.p32operate.business.mappers;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IRoomMapper;
import dk.aau.sw3.p32operate.business.mappers.interfaces.IRoomRecordMapper;
import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomMapper implements IRoomMapper {
    private final IRoomRecordMapper recordMapper;

    public RoomMapper(IRoomRecordMapper roomRecordMapper){
        recordMapper = roomRecordMapper;
    }

    @Override
    public Room mapToRoom(RoomEntity roomEntity){
        List<RoomRecord> records = new ArrayList<>();
        Room room = new Room();
        for(RoomRecordEntity re : roomEntity.getRoomRecordings()){
            records.add(recordMapper.mapToRoomRecord(re, room));
        }
        room.setRecords(records);
        room.setRoomId(roomEntity.getRoomId());
        room.setRoomName(roomEntity.getRoomName());
        return room;
    }
}
