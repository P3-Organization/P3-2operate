package dk.aau.sw3.p32operate.business.repositoryInterfaces;

import dk.aau.sw3.p32operate.business.models.PriceEntry;

import java.util.Date;
import java.util.List;

public interface IElectricityPriceRepository {
    List<PriceEntry> getElectricityPricesFromDate(Date date);
}
