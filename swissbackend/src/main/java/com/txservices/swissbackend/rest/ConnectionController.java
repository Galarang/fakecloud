package com.txservices.swissbackend.rest;

import com.txservices.swissbackend.dto.ConnectionDTO;
import com.txservices.swissbackend.dto.DurationDTO;
import com.txservices.swissbackend.mapper.Mapper;
import com.txservices.swissbackend.parsing.SwissApiParser;
import com.txservices.swissbackend.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * ConnectionController is class used as RestController, it receives requests from UI and sends back the response
 *
 * @author Dusan Batinica
 *
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ConnectionController {

    private ConnectionService connectionService;

    private SwissApiParser swissApiParser;

    @Autowired
    public ConnectionController(ConnectionService connectionService, SwissApiParser swissApiParser) {
        this.connectionService = connectionService;
        this.swissApiParser = swissApiParser;
    }

    @PostMapping("/connections")
    public ResponseEntity getDuration(@RequestBody @Valid ConnectionDTO connectionDTO) {
        DurationDTO durationDTO = connectionService.getDuration(Mapper.INSTANCE.connectionDTOToConnection(connectionDTO));
        ResponseEntity responseEntity = new ResponseEntity(durationDTO, HttpStatus.OK);
        return responseEntity;
    }
}
