package dk.aau.sw3.p32operate.dataAccess.repositories;

import dk.aau.sw3.p32operate.dataAccess.entities.RoomEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IRoomRepository extends Repository<RoomEntity, Integer> {
    List<RoomEntity> findAll();
    Optional<RoomEntity> findByRoomId(int id);

    void save(RoomEntity e);
    void delete(RoomEntity e);
}
