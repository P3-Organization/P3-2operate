package dk.aau.sw3.p32operate.business.mappers.interfaces;

import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;

public interface IRoomRecordMapper {
    RoomRecord mapToRoomRecord(RoomRecordEntity roomRecordEntity, Room room);
}
