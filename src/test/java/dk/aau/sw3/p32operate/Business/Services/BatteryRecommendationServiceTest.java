package dk.aau.sw3.p32operate.Business.Services;

import dk.aau.sw3.p32operate.business.services.BatteryRecommendationService;
import dk.aau.sw3.p32operate.business.models.BatteryRecommendationModel;
import dk.aau.sw3.p32operate.business.models.ElectricityPriceModel;
import dk.aau.sw3.p32operate.business.models.PriceEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BatteryRecommendationServiceTest {

    private BatteryRecommendationService service;

    @BeforeEach
    void setUp() {
        service = new BatteryRecommendationService();
    }

    @Test
    void shouldFindBestThreeHourRecommendation() {

        LocalDate date = LocalDate.of(2026, 11, 15);

        double[] hourlyPrices = new double[24];

        // Start by making every hour cost 1 DKK/kWh
        Arrays.fill(hourlyPrices, 1.0);

        // Cheap period: 02:00-05:00
        hourlyPrices[2] = 0.30;
        hourlyPrices[3] = 0.40;
        hourlyPrices[4] = 0.50;

        // Expensive period: 18:00-21:00
        hourlyPrices[18] = 1.80;
        hourlyPrices[19] = 1.90;
        hourlyPrices[20] = 1.70;

        ElectricityPriceModel model =
                createElectricityPriceModel(date, hourlyPrices);

        Optional<BatteryRecommendationModel> result =
                service.calculateBatteryRecommendation(model);

        assertTrue(result.isPresent());

        BatteryRecommendationModel recommendation = result.get();

        assertEquals(2, recommendation.chargeStart().getHour());
        assertEquals(5, recommendation.chargeEnd().getHour());

        assertEquals(18, recommendation.useStart().getHour());
        assertEquals(21, recommendation.useEnd().getHour());

        assertEquals(3, recommendation.durationHours());
    }

    private ElectricityPriceModel createElectricityPriceModel(
            LocalDate date,
            double[] hourlyPrices) {

        ElectricityPriceModel model =
                new ElectricityPriceModel(date);

        List<PriceEntry> entries = new ArrayList<>();

        for (int hour = 0; hour < hourlyPrices.length; hour++) {

            OffsetDateTime start =
                    date.atTime(hour, 0)
                            .atOffset(ZoneOffset.UTC);

            PriceEntry entry =
                    new PriceEntry(
                            hourlyPrices[hour],
                            0.0,
                            0.0,
                            start,
                            start.plusHours(1)
                    );

            entries.add(entry);
        }

        model.addElectricityPrices(entries);

        return model;
    }
}