package dk.aau.sw3.p32operate.presentation.controllers;

import dk.aau.sw3.p32operate.business.services.ElectricityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class PriceTestController {

    private ElectricityService electricityService;

    public PriceTestController(ElectricityService electricityService) {
        this.electricityService = electricityService;
    }

    @GetMapping("/price")
    public void price() {
        LocalDate date = LocalDate.now();
        var prices = electricityService.getElectricityPricesFromDate(date);
        if (prices != null) {
            for (var priceEntry : prices.getPriceEntries()) {
                if (priceEntry != null) {
                    System.out.println("Time: " + priceEntry.timeStart() + " Price: " + priceEntry.dkkPerKwh() + "DKK");
                }
            }
        }
    }
}
