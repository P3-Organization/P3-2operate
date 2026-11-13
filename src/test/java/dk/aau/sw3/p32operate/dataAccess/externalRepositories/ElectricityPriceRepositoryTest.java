package dk.aau.sw3.p32operate.dataAccess.externalRepositories;

import dk.aau.sw3.p32operate.business.models.PriceEntry;
import dk.aau.sw3.p32operate.business.repositoryInterfaces.IElectricityPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


public class ElectricityPriceRepositoryTest {
    private final String baseUrl = "https://www.elprisenligenu.dk/api/v1/prices";

    private IElectricityPriceRepository repository;
    private MockRestServiceServer server;
    RestClient restClient;

    @BeforeEach
    public void setup() {
        RestClient.Builder builder = RestClient.builder().baseUrl(baseUrl);
        server = MockRestServiceServer.bindTo(builder).build();
        repository = new ElectricityPriceRepository(builder.build());

    }

    /*
    * Tests that date is formatted properly to a link, and that it can deserialize a JSON.
    * */
    @Test
    void returnsPricesForDate() {
        server.expect(requestTo(baseUrl + "/2026/03-05_DK1.json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                  [
                    {"DKK_per_kWh": 0.52, "EUR_per_kWh": 0.07, "EXR": 7.45,
                     "time_start": "2024-03-05T00:00:00+01:00",
                     "time_end": "2024-03-05T01:00:00+01:00"}
                  ]
                  """, MediaType.APPLICATION_JSON));

        List<PriceEntry> result = repository.getElectricityPricesFromDate(LocalDate.of(2026, 3, 5));

        assertThat(result).hasSize(1);
        server.verify();
    }

    /*
    * If API returns 404, we check that it returns null
    * */
    @Test
    void returnsNullWhenApiReturns404() {
        server.expect(anything()).andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThat(repository.getElectricityPricesFromDate(LocalDate.of(2024, 3, 5))).isNull();
    }

    /*
     * If API returns 500 (internal server error), we check that it returns null
     * */
    @Test
    void returnsNullWhenApiReturns500() {
        server.expect(anything()).andRespond(withServerError());

        assertThat(repository.getElectricityPricesFromDate(LocalDate.of(2024, 3, 5))).isNull();
    }

    /*
     * If list is empty, we check that it returns null
     * */
    @Test
    void returnsNullWhenListIsEmpty() {
        server.expect(anything())
                .andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));

        assertThat(repository.getElectricityPricesFromDate(LocalDate.of(2024, 3, 5))).isNull();
    }

}
