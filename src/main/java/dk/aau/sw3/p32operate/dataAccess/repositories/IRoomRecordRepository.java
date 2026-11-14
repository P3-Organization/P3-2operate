package dk.aau.sw3.p32operate.dataAccess.repositories;

import dk.aau.sw3.p32operate.dataAccess.entities.RoomRecordEntity;
import org.springframework.data.repository.Repository;

public interface IRoomRecordRepository extends Repository<RoomRecordEntity, Integer> {
    void save(RoomRecordEntity e);
}
