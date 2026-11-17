package dk.aau.sw3.p32operate.business.models;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ElectricityPriceModel {
    private List<PriceEntry> priceEntries = new ArrayList<PriceEntry>();
    @Getter
    private LocalDate date; // Keep track of what date this object has prices for

    public ElectricityPriceModel(LocalDate date) {
        this.date = date;
    }

    public void addElectricityPrices(List<PriceEntry> list) {
        if (priceEntries.isEmpty()) {
            priceEntries.addAll(list);
        } else {
            System.out.println("This list already has electricity prices");
        }
    }

    public List<PriceEntry> getPriceEntries() { return priceEntries; }

}
