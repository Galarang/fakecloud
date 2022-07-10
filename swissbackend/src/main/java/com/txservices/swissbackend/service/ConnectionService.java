package com.txservices.swissbackend.service;

import com.txservices.swissbackend.dto.DurationDTO;
import com.txservices.swissbackend.entity.Connection;
import com.txservices.swissbackend.parsing.SwissApiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ConnectionService is class used as Service, it receives command for duration from Controller and responds with DurationDTO object
 *
 * @author Dusan Batinica
 *
 */

@Service
public class ConnectionService {

    private SwissApiParser swissApiParser;

    @Autowired
    public ConnectionService(SwissApiParser swissApiParser) {
        this.swissApiParser = swissApiParser;
    }

    public DurationDTO getDuration(Connection connection) {
        return swissApiParser.getDuration(connection);
    }

}
