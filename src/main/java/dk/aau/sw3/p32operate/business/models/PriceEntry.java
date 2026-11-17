package dk.aau.sw3.p32operate.business.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record PriceEntry(
        @JsonProperty("DKK_per_kWh") double dkkPerKwh,
        @JsonProperty("EUR_per_kWh") double eurPerKwh,
        @JsonProperty("EXR") double exr,
        @JsonProperty("time_start") OffsetDateTime timeStart,
        @JsonProperty("time_end") OffsetDateTime timeEnd) {}