package dk.aau.sw3.p32operate.business.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
//This class defines all parameters relevant for each room data where the 'equals' method checks
// if any id's match. If they do, then they will be percived as the same
//This is a Java Object, and also where changes can be stored
public class RoomRecord {
    private Integer id;

    private Room room;

    private LocalDateTime time;

    private Double humidity;

    private Double hoaHumidity;

    private Double temperature;

    private Double hoaTemperature;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomRecord that = (RoomRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private Double signalTemperature;
}
