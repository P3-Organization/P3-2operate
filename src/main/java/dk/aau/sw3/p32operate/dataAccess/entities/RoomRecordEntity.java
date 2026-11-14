package dk.aau.sw3.p32operate.dataAccess.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "room_recordings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomRecordEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "hoa_humidity")
    private Double hoaHumidity;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "hoa_temperature")
    private Double hoaTemperature;

    @Column(name = "signal_temperature")
    private Double signalTemperature;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomRecordEntity that = (RoomRecordEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
