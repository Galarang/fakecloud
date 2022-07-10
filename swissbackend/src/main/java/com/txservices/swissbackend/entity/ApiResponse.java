package com.txservices.swissbackend.entity;

import com.txservices.swissbackend.dto.DurationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * ApiResponse is entity used to store response from Swiss public transport API (list of durations between point A and B)
 *
 * @author Dusan Batinica
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    List<DurationDTO> connections;
}
