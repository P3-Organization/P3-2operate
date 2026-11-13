package dk.aau.sw3.p32operate.business.mappers;

import dk.aau.sw3.p32operate.business.models.Consumption;
import dk.aau.sw3.p32operate.dataAccess.entities.ConsumptionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumptionMapper {
    public Consumption mapToConsumption(ConsumptionEntity entity){
        return new Consumption(entity.getDate(), entity.getKwh(), entity.getKr(), entity.getMin_kr_total());
    }

    public List<Consumption> mapToConsumptionList(List<ConsumptionEntity> entities){
        List<Consumption> consumptions = new ArrayList<>();
        for(ConsumptionEntity e : entities){
            consumptions.add(mapToConsumption(e));
        }
        return consumptions;
    }
}
