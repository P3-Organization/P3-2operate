package dk.aau.sw3.p32operate.dataAccess.repositories;

import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IConsumptionRepository extends Repository<ConsumptionEntity, LocalDateTime> {
    List<ConsumptionEntity> findAll();
    Optional<ConsumptionEntity> findByDate(LocalDateTime date);
}
