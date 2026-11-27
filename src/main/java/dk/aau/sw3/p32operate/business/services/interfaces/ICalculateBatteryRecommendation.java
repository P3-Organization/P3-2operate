package dk.aau.sw3.p32operate.business.services.interfaces;

import dk.aau.sw3.p32operate.business.models.BatteryRecommendationModel;
import dk.aau.sw3.p32operate.business.models.ElectricityPriceModel;

import java.util.Optional;
import java.time.LocalTime;
import java.time.OffsetDateTime;

public interface ICalculateBatteryRecommendation {

    Optional<BatteryRecommendationModel> calculateBatteryRecommendation(
            ElectricityPriceModel electricityPriceModel,
            OffsetDateTime currentTime,
            LocalTime earliestActionTime,
            LocalTime latestActionTime
    );


}
