package com.txservices.swissbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Connection is the main entity used to store info about our trip
 *
 * @author Dusan Batinica
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Connection {

    private String locationFrom;

    private String locationTo;

    private Transportation transportation;

    private List<String> via;
}
