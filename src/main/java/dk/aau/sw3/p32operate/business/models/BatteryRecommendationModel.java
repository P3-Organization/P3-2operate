package dk.aau.sw3.p32operate.business.models;

import java.time.OffsetDateTime;

public record BatteryRecommendationModel(
        OffsetDateTime chargeStart,
        OffsetDateTime chargeEnd,
        OffsetDateTime useStart,
        OffsetDateTime useEnd,
        int durationHours,
        double averageChargePrice,
        double averageUsePrice
) {

    public double priceDifferencePerKwh() {
        return averageUsePrice - averageChargePrice;
    }
}
