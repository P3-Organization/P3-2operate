package dk.aau.sw3.p32operate.dataAccess.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomEntity {

    @Id
    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "room_name")
    private String roomName;

    @OneToMany(mappedBy = "room")
    private List<RoomRecordEntity> roomRecordings;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(roomName, that.roomName) && Objects.equals(roomRecordings, that.roomRecordings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomName, roomRecordings);
    }

}
