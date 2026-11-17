package dk.aau.sw3.p32operate.business.mappers;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IRoomRecordMapper;
import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.business.models.RoomRecord;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import dk.aau.sw3.p32operate.dataAccess.repositories.IRoomRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class RoomRecordMapper implements IRoomRecordMapper {

    @Override
    public RoomRecord mapToRoomRecord(RoomRecordEntity roomRecordEntity, Room room){
        return new RoomRecord(
                roomRecordEntity.getId(),
                room,
                roomRecordEntity.getTime(),
                roomRecordEntity.getHumidity(),
                roomRecordEntity.getHoaHumidity(),
                roomRecordEntity.getTemperature(),
                roomRecordEntity.getHoaTemperature(),
                roomRecordEntity.getSignalTemperature()
        );
    }
}
