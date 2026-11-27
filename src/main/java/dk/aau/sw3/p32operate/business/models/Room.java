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
//A class to identify specific rooms with a name and an id as well as create a list of its records
public class Room {
    private Integer roomId;
    private String roomName;

    private List<RoomRecord> records;
}
