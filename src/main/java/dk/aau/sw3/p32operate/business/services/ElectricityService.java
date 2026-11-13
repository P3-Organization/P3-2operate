package dk.aau.sw3.p32operate.business.services;

import dk.aau.sw3.p32operate.business.models.ElectricityPriceModel;
import dk.aau.sw3.p32operate.business.models.PriceEntry;
import dk.aau.sw3.p32operate.business.repositoryInterfaces.IElectricityPriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ElectricityService {

    private IElectricityPriceRepository electricityPriceRepository;

    public ElectricityService(IElectricityPriceRepository electricityPriceRepository) {
        this.electricityPriceRepository = electricityPriceRepository;
    }

    public ElectricityPriceModel getElectricityPricesFromDate(LocalDate date) {
        ElectricityPriceModel prices = new ElectricityPriceModel(date);
        List<PriceEntry> priceEntries = electricityPriceRepository.getElectricityPricesFromDate(date);
        if (priceEntries.isEmpty()) { return null; }
        prices.addElectricityPrices(priceEntries);
        return prices;
    }
}
