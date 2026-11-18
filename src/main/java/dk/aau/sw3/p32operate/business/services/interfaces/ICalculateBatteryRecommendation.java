package dk.aau.sw3.p32operate.business.services.interfaces;

import dk.aau.sw3.p32operate.business.models.BatteryRecommendationModel;
import dk.aau.sw3.p32operate.business.models.ElectricityPriceModel;

import java.util.Optional;


public interface ICalculateBatteryRecommendation {

    Optional<BatteryRecommendationModel> calculateBatteryRecommendation(
            ElectricityPriceModel electricityPriceModel
    );


}
