package dk.aau.sw3.p32operate.business.mappers.interfaces;

import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;

public interface IRoomMapper {
    Room mapToRoom(RoomEntity roomEntity);
}
