package dk.aau.sw3.p32operate.presentation.dto;

import java.time.LocalDateTime;

public record ConsumptionDTO(
        LocalDateTime date,
        double kwh,
        double kr,
        double min_kr
) {
}
