package dk.aau.sw3.p32operate.business.mappers.interfaces;

import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;

import java.util.List;

public interface IConsumptionMapper {

    Consumption mapToConsumption(ConsumptionEntity entity);

    List<Consumption> mapToConsumptionList(List<ConsumptionEntity> entities);
}
