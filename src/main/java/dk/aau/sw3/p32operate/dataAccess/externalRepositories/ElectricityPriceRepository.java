package dk.aau.sw3.p32operate.dataAccess.externalRepositories;

import dk.aau.sw3.p32operate.business.models.PriceEntry;
import dk.aau.sw3.p32operate.business.repositoryInterfaces.IElectricityPriceRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;
import reactor.netty.http.server.HttpServer;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElectricityPriceRepository implements IElectricityPriceRepository {
    private final RestClient restClient;

    public ElectricityPriceRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<PriceEntry> getElectricityPricesFromDate(LocalDate date) throws HttpServerErrorException {

        List<PriceEntry> result = new ArrayList<PriceEntry>();
        String uri = String.format("/%s/%s_DK1.json", date.getYear(), date.format(DateTimeFormatter.ofPattern("MM-dd")));
        try {
            result = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new HttpServerErrorException(response.getStatusCode());
                    })
                    .body(new ParameterizedTypeReference<List<PriceEntry>>() {});
        } catch (HttpServerErrorException ex) {
            System.out.println("Fetching electricity prices failed with the following error: " + ex.getMessage());
        }
        
        if (result == null || result.isEmpty()) return null;

        return result;
    }

}
