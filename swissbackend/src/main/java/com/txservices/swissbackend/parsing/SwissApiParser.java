package com.txservices.swissbackend.parsing;

import com.txservices.swissbackend.entity.ApiResponse;
import com.txservices.swissbackend.dto.DurationDTO;
import com.txservices.swissbackend.entity.Connection;
import com.txservices.swissbackend.exception.ConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SwissApiParser is used to form request for Swiss public transport API and store response from Swiss public transport API
 *
 * @author Dusan Batinica
 *
 */

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SwissApiParser {

    private TimeParser timeParser;

    private StringBuilder swissApiUrl;

    private final String from;

    private final String to;

    private final String transportation;

    private final String via;

    /**
     * The is used to limit the fields received from Swiss Public Transport API to only duration that takes to go from
     * point A to point B
     */
    private final String limitFields;

    private RestTemplate restTemplate;

    @Autowired
    public SwissApiParser(TimeParser timeParser, RestTemplate restTemplate) {
        this.timeParser = timeParser;
        this.restTemplate = restTemplate;
        this.swissApiUrl = new StringBuilder("http://transport.opendata.ch/v1/connections");
        this.from = "?from=";
        this.to = "&to=";
        this.transportation = "&transportations[]=";
        this.via = "&via[]=";
        this.limitFields = "&fields[]=connections/duration";
    }

    /**
     * This is a method that is utilized to get average duration needed
     * to get from point A to point B
     *
     * @param connection is the object of type Connection (mapped from ConnectionDTO)
     * @return object of type DurationDTO that contains duration as a field
     */
    public DurationDTO getDuration(Connection connection) {
        swissApiUrl.append(from + connection.getLocationFrom()).append(to + connection.getLocationTo())
                   .append(transportation + connection.getTransportation().toString().toLowerCase());
        if (connection.getVia() != null && !connection.getVia().isEmpty()) {
            connection.getVia().forEach(x -> swissApiUrl.append(via + x));
        }
        swissApiUrl.append(limitFields);
        ApiResponse apiResponse = restTemplate.getForObject(swissApiUrl.toString(), ApiResponse.class);
        if (!apiResponse.getConnections().isEmpty()) {
            List<String> durations = apiResponse.getConnections().stream().map(durationDTO -> durationDTO.getDuration()).collect(Collectors.toList());
            String avgDuration = timeParser.getDuration(durations);
            DurationDTO durationResult = new DurationDTO(avgDuration);
            return durationResult;
        } else {
            throw new ConnectionException("Results for this request do not exist.");
        }
    }
}
