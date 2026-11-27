package dk.aau.sw3.p32operate.dataAccess.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

//this entity is the full record of the data for the rooms (roomId, Room name, and room recordings) as it is stored in the DB
//the 'equals' function compares the different parameters for rooms and ensures that the id, names, and recordings match
//This data is being stored/represented in an SQL-table

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
