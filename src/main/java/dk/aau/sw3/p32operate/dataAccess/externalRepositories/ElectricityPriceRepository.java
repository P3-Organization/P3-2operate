package dk.aau.sw3.p32operate.dataAccess.externalRepositories;

import dk.aau.sw3.p32operate.business.repositoryInterfaces.IElectricityPriceRepository;
import org.springframework.web.client.RestClient;

public class ElectricityPriceRepository implements IElectricityPriceRepository {
    private RestClient restClient;

    public ElectricityPriceRepository(RestClient restClient) {
        this.restClient = restClient;
    }


}
