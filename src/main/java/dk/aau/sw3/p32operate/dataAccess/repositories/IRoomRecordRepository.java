package dk.aau.sw3.p32operate.dataAccess.repositories;

import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IRoomRecordRepository extends Repository<RoomRecordEntity, Integer> {
    Optional<RoomRecordEntity> findById(Integer id);

    List<RoomRecordEntity> findAll();

    void save(RoomRecordEntity e);

    void delete(RoomRecordEntity e);
}
