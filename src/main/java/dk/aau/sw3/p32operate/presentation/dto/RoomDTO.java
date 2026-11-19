package dk.aau.sw3.p32operate.presentation.dto;
import java.util.List;

public record RoomDTO(
        Integer roomId,
        String roomName,

        List<RoomRecordDTO> roomRecordDTOS
) {
}
