package dk.aau.sw3.p32operate.dataAccess.externalRepositories;

import dk.aau.sw3.p32operate.business.models.PriceEntry;
import dk.aau.sw3.p32operate.business.repositoryInterfaces.IElectricityPriceRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.Date;
import java.util.List;

@Repository
public class ElectricityPriceRepository implements IElectricityPriceRepository {
    private RestClient restClient;

    public ElectricityPriceRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<PriceEntry> getElectricityPricesFromDate(Date date) {
        // Todo implement the call to the external API and handle the data. 
        return null;
    }

}
