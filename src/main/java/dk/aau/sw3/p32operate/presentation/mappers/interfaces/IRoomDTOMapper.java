package dk.aau.sw3.p32operate.presentation.mappers.interfaces;

import dk.aau.sw3.p32operate.business.models.Room;
import dk.aau.sw3.p32operate.presentation.dto.RoomDTO;

public interface IRoomDTOMapper {
    RoomDTO mapToRoomDTO(Room room);
}
