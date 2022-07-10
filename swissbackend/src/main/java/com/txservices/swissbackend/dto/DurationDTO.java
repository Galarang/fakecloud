package com.txservices.swissbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DurationDTO is class used to store data from JSON sent by Swiss public transport API and back to UI
 *
 * @author Dusan Batinica
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DurationDTO {

    private String duration;
}
