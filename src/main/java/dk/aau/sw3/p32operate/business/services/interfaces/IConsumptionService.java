package dk.aau.sw3.p32operate.business.services.interfaces;

import dk.aau.sw3.p32operate.business.mappers.ConsumptionMapper;
import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.dataAccess.repositories.IConsumptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IConsumptionService {
    public List<Consumption> getAll();

    public Optional<Consumption> getByDate(LocalDateTime date);
}
