package com.txservices.swissbackend.dto;

import com.txservices.swissbackend.entity.Transportation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ConnectionDTO is class used to store data provided by user on UI (JSON sent by UI)
 * Please see the {@link com.txservices.swissbackend.entity.Connection} class for main entity
 *
 * @author Dusan Batinica
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionDTO {

    @NotNull(message = "LocationFrom MUST NOT be NULL.")
    @Valid
    private String locationFrom;

    @NotNull(message = "LocationTo MUST NOT be NULL.")
    @Valid
    private String locationTo;

    private Transportation transportation;

    private List<String> via;
}
