package airborne.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPostIdException extends ResponseStatusException {
    public InvalidPostIdException(){super(HttpStatus.BAD_REQUEST, "POST_INVALID");}
    public InvalidPostIdException(String errorCode){super(HttpStatus.BAD_REQUEST, errorCode);}
}
