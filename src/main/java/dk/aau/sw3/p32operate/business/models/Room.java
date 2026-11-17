package dk.aau.sw3.p32operate.business.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Integer roomId;
    private String roomName;

    private List<RoomRecord> records;
}
