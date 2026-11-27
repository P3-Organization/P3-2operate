package dk.aau.sw3.p32operate.business.services;

import dk.aau.sw3.p32operate.business.models.BatteryRecommendationModel;
import dk.aau.sw3.p32operate.business.models.ElectricityPriceModel;
import dk.aau.sw3.p32operate.business.models.PriceEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BatteryRecommendationServiceTest {

    private BatteryRecommendationService service;
    private LocalDate date;
    private LocalTime earliestActionTime;
    private LocalTime latestActionTime;

    @BeforeEach
    void setUp() {
        service = new BatteryRecommendationService();
        date = LocalDate.of(2026, 11, 15);

        earliestActionTime = LocalTime.of(6, 0);

        latestActionTime = LocalTime.of(22, 0);
    }

    @Test
    void shouldFindBestThreeHourRecommendation() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        // Cheap 3-hour charging period
        hourlyPrices[7] = 0.30;
        hourlyPrices[8] = 0.40;
        hourlyPrices[9] = 0.50;

        // Expensive 3-hour usage period
        hourlyPrices[18] = 1.80;
        hourlyPrices[19] = 1.90;
        hourlyPrices[20] = 1.70;

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);


        BatteryRecommendationModel recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime).orElseThrow();

        assertEquals(7, recommendation.chargeStart().getHour());
        assertEquals(10, recommendation.chargeEnd().getHour());
        assertEquals(18, recommendation.useStart().getHour());
        assertEquals(21, recommendation.useEnd().getHour());
        assertEquals(3, recommendation.durationHours());
        assertEquals(0.40, recommendation.averageChargePrice(), 0.0001);
        assertEquals(1.80, recommendation.averageUsePrice(), 0.0001);

    }

    @Test
    void shouldReturnEmptyWhenPriceDifferenceIsTooSmall() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        hourlyPrices[18] = 1.19;

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        Optional<BatteryRecommendationModel> result =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldIgnoreChargingPeriodsInThePast() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        // Very cheap, but already in the past.
        hourlyPrices[7] = 0.10;
        hourlyPrices[8] = 0.10;
        hourlyPrices[9] = 0.10;

        // Best available future charging period.
        hourlyPrices[13] = 0.50;
        hourlyPrices[14] = 0.50;
        hourlyPrices[15] = 0.50;

        hourlyPrices[18] = 2.00;
        hourlyPrices[19] = 2.00;
        hourlyPrices[20] = 2.00;

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(12, 0).atOffset(ZoneOffset.UTC);

        BatteryRecommendationModel recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime).orElseThrow();

        assertEquals(13, recommendation.chargeStart().getHour());
    }

    @Test
    void shouldIgnorePeriodsOutsideActionHours() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        // Cheapest period, but outside the allowed action hours.
        hourlyPrices[3] = 0.0;
        hourlyPrices[4] = 0.0;
        hourlyPrices[5] = 0.0;

        // Valid charging period.
        hourlyPrices[7] = 0.50;
        hourlyPrices[8] = 0.50;
        hourlyPrices[9] = 0.50;

        hourlyPrices[18] = 2.0;
        hourlyPrices[19] = 2.0;
        hourlyPrices[20] = 2.0;

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(0, 0).atOffset(ZoneOffset.UTC);

        BatteryRecommendationModel recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime).orElseThrow();

        assertEquals(7, recommendation.chargeStart().getHour());
    }


    @Test
    void shouldFindBestTwoHourRecommendation() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        // Two very cheap hours
        hourlyPrices[7] = 0.0;
        hourlyPrices[8] = 0.0;

        // Two very expensive hours
        hourlyPrices[18] = 3.0;
        hourlyPrices[19] = 3.0;

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        BatteryRecommendationModel recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime).orElseThrow();

        assertEquals(7, recommendation.chargeStart().getHour());
        assertEquals(9, recommendation.chargeEnd().getHour());

        assertEquals(18, recommendation.useStart().getHour());
        assertEquals(20, recommendation.useEnd().getHour());

        assertEquals(2, recommendation.durationHours());

        assertEquals(0.0, recommendation.averageChargePrice(), 0.0001);

        assertEquals(3, recommendation.averageUsePrice(), 0.0001);
    }

    @Test
    void shouldFindBestOneHourRecommendation() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        // One really cheap hour
        hourlyPrices[7] = 0.0;

        // One really expensive hour
        hourlyPrices[18] = 3.0;

        // Make the hours beside the expensive one unattractive

        hourlyPrices[17] = 0.0;
        hourlyPrices[19] = 0.0;

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        BatteryRecommendationModel recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime).orElseThrow();


        assertEquals(7, recommendation.chargeStart().getHour());
        assertEquals(18, recommendation.useStart().getHour());
        assertEquals(1, recommendation.durationHours());
    }

    @Test
    void shouldReturnNoRecommendationWhenThereIsNoPriceAdvantage() {

        double[] hourlyPrices = new double[24];

        // Every hour has exactly the same price.
        Arrays.fill(hourlyPrices, 1.0);

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        Optional<BatteryRecommendationModel> recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime);

        assertTrue(recommendation.isEmpty());
    }

    @Test
    void shouldReturnNoRecommendationForNullModel() {

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        Optional<BatteryRecommendationModel> recommendation =
                service.calculateBatteryRecommendation(null, currentTime, earliestActionTime, latestActionTime);

        assertTrue(recommendation.isEmpty());
    }

    @Test
    void shouldReturnNoRecommendationWhenThereAreTooFewPrices() {

        ElectricityPriceModel model = new ElectricityPriceModel(date);

        OffsetDateTime start = date.atTime(7, 0).atOffset(ZoneOffset.UTC);

        List<PriceEntry> entries = new ArrayList<>();

        entries.add(new PriceEntry(1.0, 0.0, 0.0, start, start.plusHours(1)));

        model.addElectricityPrices(entries);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        Optional<BatteryRecommendationModel> recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime);

        assertTrue(recommendation.isEmpty());
    }

    @Test
    void shouldSortPricesBeforeCalculatingRecommendation() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        hourlyPrices[7] = 0.30;
        hourlyPrices[8] = 0.40;
        hourlyPrices[9] = 0.50;

        hourlyPrices[18] = 1.80;
        hourlyPrices[19] = 1.90;
        hourlyPrices[20] = 1.70;

        List<PriceEntry> entries =
                createPriceEntries(hourlyPrices);

        Collections.reverse(entries);


        ElectricityPriceModel model =
               new ElectricityPriceModel(date);


        model.addElectricityPrices(entries);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        BatteryRecommendationModel recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime).orElseThrow();

        assertEquals(7, recommendation.chargeStart().getHour());
        assertEquals(18, recommendation.useStart().getHour());
        assertEquals(3, recommendation.durationHours());
    }

    @Test
    void shouldNotTreatMissingHoursAsContinuous() {

        ElectricityPriceModel model = new ElectricityPriceModel(date);

        List<PriceEntry> entries = new ArrayList<>();

        entries.add(createPriceEntry(7, 8, 0.10));

        // Notice: 08:00-09:00 is missing
        entries.add(createPriceEntry(9, 10, 0.10));

        entries.add(createPriceEntry(18, 19, 3.00));

        entries.add(createPriceEntry(19, 20, 3.00));

        model.addElectricityPrices(entries);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        BatteryRecommendationModel recommendation =
                service.calculateBatteryRecommendation(model, currentTime, earliestActionTime, latestActionTime).orElseThrow();

        assertEquals(1, recommendation.durationHours());
    }

    @Test
    void shouldReturnNoRecommendationForInvalidActionHours() {

        double[] hourlyPrices = new double[24];
        Arrays.fill(hourlyPrices, 1.0);

        ElectricityPriceModel model = createElectricityPriceModel(hourlyPrices);

        OffsetDateTime currentTime = date.atTime(6, 0).atOffset(ZoneOffset.UTC);

        Optional<BatteryRecommendationModel> recommendation =
                service.calculateBatteryRecommendation(model, currentTime, LocalTime.of(22, 0), LocalTime.of(6, 0));

        assertTrue(recommendation.isEmpty());
    }


    private ElectricityPriceModel createElectricityPriceModel(double[] hourlyPrices) {

        ElectricityPriceModel model = new ElectricityPriceModel(date);

        List<PriceEntry> entries = createPriceEntries(hourlyPrices);

        model.addElectricityPrices(entries);

        return model;
    }


    private List<PriceEntry> createPriceEntries(double[] hourlyPrices) {

        List<PriceEntry> entries = new ArrayList<>();

        for (int hour = 0; hour < hourlyPrices.length; hour++) {

            OffsetDateTime start = date.atTime(hour, 0).atOffset(ZoneOffset.UTC);

            entries.add(new PriceEntry(hourlyPrices[hour], 0.0, 0.0, start, start.plusHours(1)
                    )
            );
        }

        return entries;
    }


    private PriceEntry createPriceEntry(int startHour, int endHour, double price) {

        OffsetDateTime start = date.atTime(startHour, 0).atOffset(ZoneOffset.UTC);

        OffsetDateTime end = date.atTime(endHour, 0).atOffset(ZoneOffset.UTC);

        return new PriceEntry(price, 0.0, 0.0, start, end);
    }
}