package dk.aau.sw3.p32operate.business.models;

import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RoomRecord {
    private Integer id;

    private Room room;

    private LocalDateTime time;

    private Double humidity;

    private Double hoaHumidity;

    private Double temperature;

    private Double hoaTemperature;

    private Double signalTemperature;
}
