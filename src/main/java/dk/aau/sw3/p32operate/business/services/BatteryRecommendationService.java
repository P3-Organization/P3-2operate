package dk.aau.sw3.p32operate.business.services;

import dk.aau.sw3.p32operate.business.models.BatteryRecommendationModel;
import dk.aau.sw3.p32operate.business.models.ElectricityPriceModel;
import dk.aau.sw3.p32operate.business.models.PriceEntry;
import dk.aau.sw3.p32operate.business.services.interfaces.ICalculateBatteryRecommendation;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BatteryRecommendationService implements ICalculateBatteryRecommendation {

    private static final int MAX_DURATION_HOURS = 3;
    private static final double MIN_AVERAGE_PRICE_DIFFERENCE = 0.20;

    @Override
    public Optional<BatteryRecommendationModel> calculateBatteryRecommendation(
            ElectricityPriceModel electricityPriceModel,
            OffsetDateTime currentTime,
            LocalTime earliestActionTime,
            LocalTime latestActionTime
    ) {

        if (electricityPriceModel == null || currentTime == null
                || earliestActionTime == null || latestActionTime == null) {
            return Optional.empty();
        }

        if (!earliestActionTime.isBefore(latestActionTime)) {
            return Optional.empty();
        }

        List<PriceEntry> prices = new ArrayList<>(electricityPriceModel.getPriceEntries());

        if (prices.size() < 2) {
            return Optional.empty();
        }

        prices.sort(Comparator.comparing(PriceEntry::timeStart));

        BatteryRecommendationModel bestRecommendation = null;
        double bestScore = 0.0;

        for (int duration = 1; duration <= MAX_DURATION_HOURS; duration++) {
            for (int chargeStart = 0; chargeStart + (2 * duration) <= prices.size(); chargeStart++) {
                int earliestUseStart = chargeStart + duration;

                for (int useStart = earliestUseStart; useStart + duration <= prices.size(); useStart++) {

                    // The entries in each period must actually be continuous.
                    if (!isContinuousPeriod(prices, chargeStart, duration)) {
                        continue;
                    }

                    if (!isContinuousPeriod(prices, useStart, duration)) {
                        continue;
                    }

                    OffsetDateTime chargeStartTime = prices.get(chargeStart).timeStart();
                    OffsetDateTime chargeEndTime = prices.get(chargeStart + duration - 1).timeEnd();
                    OffsetDateTime useStartTime = prices.get(useStart).timeStart();
                    OffsetDateTime useEndTime = prices.get(useStart + duration - 1).timeEnd();

                    // Charging must not start in the past
                    if (chargeStartTime.isBefore(currentTime)) {
                        continue;
                    }

                    // Both periods must be possible while staff are at the motel
                    if (!isWithinActionHours(
                            chargeStartTime,
                            chargeEndTime,
                            earliestActionTime,
                            latestActionTime
                    )) {
                        continue;
                    }

                    if (!isWithinActionHours(
                            useStartTime,
                            useEndTime,
                            earliestActionTime,
                            latestActionTime
                    )) {
                        continue;
                    }

                    double chargePriceSum = sumPrices(prices, chargeStart, duration);
                    double usePriceSum = sumPrices(prices, useStart, duration);

                    double averageChargePrice = chargePriceSum / duration;
                    double averageUsePrice = usePriceSum / duration;
                    double averagePriceDifference = averageUsePrice - averageChargePrice;

                    // Ignore price differences that are too small
                    if (averagePriceDifference < MIN_AVERAGE_PRICE_DIFFERENCE) {
                        continue;
                    }

                    double score = usePriceSum - chargePriceSum;

                    if (score > bestScore) {
                        bestScore = score;

                        bestRecommendation = new BatteryRecommendationModel(
                                chargeStartTime,
                                chargeEndTime,
                                useStartTime,
                                useEndTime,
                                duration,
                                averageChargePrice,
                                averageUsePrice
                        );
                    }
                }
            }
        }

        return Optional.ofNullable(bestRecommendation);
    }

    private double sumPrices(List<PriceEntry> prices, int start, int duration) {
        double sum = 0.0;

        for (int i = start; i < start + duration; i++) {
            sum += prices.get(i).dkkPerKwh();
        }

        return sum;
    }

    private boolean isWithinActionHours(
            OffsetDateTime start,
            OffsetDateTime end,
            LocalTime earliestActionTime,
            LocalTime latestActionTime
    ) {
        if (!start.toLocalDate().equals(end.toLocalDate())) {
            return false;
        }

        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        return !startTime.isBefore(earliestActionTime) && !endTime.isAfter(latestActionTime);
    }


    private boolean isContinuousPeriod(List<PriceEntry> prices, int start, int duration) {
        for (int i = start; i < start + duration - 1; i++) {
            if (!prices.get(i).timeEnd().equals(prices.get(i + 1).timeStart())) {

                return false;
            }
        }

        return true;
    }
}




