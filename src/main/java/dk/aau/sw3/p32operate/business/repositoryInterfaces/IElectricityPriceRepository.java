package dk.aau.sw3.p32operate.business.repositoryInterfaces;

import dk.aau.sw3.p32operate.business.models.PriceEntry;

import java.time.LocalDate;
import java.util.List;

public interface IElectricityPriceRepository {
    List<PriceEntry> getElectricityPricesFromDate(LocalDate date);
}
