package dk.aau.sw3.p32operate.business.services;

import dk.aau.sw3.p32operate.business.mappers.ConsumptionMapper;
import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.business.services.interfaces.IConsumptionService;
import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import dk.aau.sw3.p32operate.dataAccess.repositories.IConsumptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionService implements IConsumptionService {
    private final IConsumptionRepository consumptionRepository;
    private final ConsumptionMapper mapper;

    public ConsumptionService(IConsumptionRepository consumptionRepository, ConsumptionMapper mapper){
        this.consumptionRepository = consumptionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Consumption> getAll(){
        List<ConsumptionEntity> entities = consumptionRepository.findAll();
        return mapper.mapToConsumptionList(entities);
    }

    @Override
    public Optional<Consumption> getByDate(LocalDateTime date){
        Optional<ConsumptionEntity> entity = consumptionRepository.findByDate(date);
        if (entity.isPresent()){
            return Optional.of(mapper.mapToConsumption(entity.get()));
        }
        return Optional.empty();
    }

}
