package dk.aau.sw3.p32operate.business.services;

import dk.aau.sw3.p32operate.dataAccess.repositories.IRoomRepository;

public class RoomService {
    IRoomRepository roomRepository;

    public RoomService(IRoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
}
