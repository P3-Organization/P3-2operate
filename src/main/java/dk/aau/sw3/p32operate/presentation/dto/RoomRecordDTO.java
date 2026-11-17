package dk.aau.sw3.p32operate.presentation.dto;

import dk.aau.sw3.p32operate.business.models.Room;

import java.time.LocalDateTime;

public record RoomRecordDTO(
        Integer id,
        RoomDTO room,
        LocalDateTime time,
        Double humidity,
        Double hoaHumidity,
        Double temperature,
        Double hoaTemperature,
        Double signalTemperature
) {
}
