package com.txservices.swissbackend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

/**
 * ConnectionErrorResponse is class for storing and representing custom error response for user
 *
 * @author Dusan Batinica
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionErrorResponse {

    private String message;

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
