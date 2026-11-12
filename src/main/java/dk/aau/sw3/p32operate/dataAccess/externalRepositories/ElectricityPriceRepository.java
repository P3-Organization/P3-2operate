package dk.aau.sw3.p32operate.dataAccess.externalRepositories;

import dk.aau.sw3.p32operate.business.models.PriceEntry;
import dk.aau.sw3.p32operate.business.repositoryInterfaces.IElectricityPriceRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

import java.util.List;

@Repository
public class ElectricityPriceRepository implements IElectricityPriceRepository {
    private RestClient restClient;

    public ElectricityPriceRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<PriceEntry> getElectricityPricesFromDate(LocalDate date) {
        // Todo implement the call to the external API and handle the data.
        var result = restClient.get()
                .uri("/{year}/{month}-{day}_DK1.json", date.getYear(), date.getMonthValue(), date.getDayOfMonth())
                .retrieve()
                .body(new ParameterizedTypeReference<List<PriceEntry>>() {});

        if (result == null) return null;
        if (result.isEmpty()) return null;


        return result;
    }

}
