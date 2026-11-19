package dk.aau.sw3.p32operate.business.services;

import dk.aau.sw3.p32operate.business.models.BatteryRecommendationModel;
import dk.aau.sw3.p32operate.business.models.ElectricityPriceModel;
import dk.aau.sw3.p32operate.business.models.PriceEntry;
import dk.aau.sw3.p32operate.business.services.interfaces.ICalculateBatteryRecommendation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BatteryRecommendationService
        implements ICalculateBatteryRecommendation {

    private static final int MAX_DURATION_HOURS = 3;

    @Override
    public Optional<BatteryRecommendationModel> calculateBatteryRecommendation(
            ElectricityPriceModel electricityPriceModel) {

        if (electricityPriceModel == null) {
            return Optional.empty();
        }

        List<PriceEntry> prices =
                new ArrayList<>(electricityPriceModel.getPriceEntries());

        if (prices.size() < 2) {
            return Optional.empty();
        }

        prices.sort(
                Comparator.comparing(PriceEntry::timeStart)
        );

        BatteryRecommendationModel bestRecommendation = null;
        double bestScore = 0.0;

        for (int duration = 1;
             duration <= MAX_DURATION_HOURS;
             duration++) {

            for (int chargeStart = 0; chargeStart + (2 * duration) <= prices.size(); chargeStart++) {

                int earliestUseStart =
                        chargeStart + duration;

                for (int useStart = earliestUseStart; useStart + duration <= prices.size(); useStart++) {

                    double chargePriceSum =
                            sumPrices(
                                    prices,
                                    chargeStart,
                                    duration
                            );

                    double usePriceSum =
                            sumPrices(
                                    prices,
                                    useStart,
                                    duration
                            );

                    double score =
                            usePriceSum - chargePriceSum;

                    if (score > bestScore) {

                        bestScore = score;

                        double averageChargePrice =
                                chargePriceSum / duration;

                        double averageUsePrice =
                                usePriceSum / duration;

                        bestRecommendation =
                                new BatteryRecommendationModel(
                                        prices.get(chargeStart)
                                                .timeStart(),

                                        prices.get(
                                                chargeStart
                                                        + duration - 1
                                        ).timeEnd(),

                                        prices.get(useStart)
                                                .timeStart(),

                                        prices.get(
                                                useStart
                                                        + duration - 1
                                        ).timeEnd(),

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

    private double sumPrices(
            List<PriceEntry> prices,
            int start,
            int duration) {

        double sum = 0.0;

        for (int i = start;
             i < start + duration;
             i++) {

            sum += prices.get(i).dkkPerKwh();
        }

        return sum;
    }
}
