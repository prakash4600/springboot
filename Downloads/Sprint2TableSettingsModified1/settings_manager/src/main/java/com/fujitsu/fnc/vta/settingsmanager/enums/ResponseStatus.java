package com.fujitsu.fnc.vta.settingsmanager.enums;

import org.springframework.http.HttpStatus;
import com.fujitsu.fnc.vta.settingsmanager.model.Constants;
public enum ResponseStatus {

	SUCCESS(Constants.SUCCESS, HttpStatus.CREATED),
	OK(Constants.OK, HttpStatus.OK),
    FAILURE(Constants.FAILURE, HttpStatus.BAD_REQUEST),
    NODATA(Constants.NODATA,HttpStatus.NOT_FOUND),
    RESOURCE_EXISTS(Constants.RESOURCE_EXISTS, HttpStatus.CONFLICT),
    RESOURCE(Constants.RESOURCE, HttpStatus.CONFLICT),
    INVALID_DATA(Constants.INVALID_DATA, HttpStatus.INTERNAL_SERVER_ERROR),
	ALREADY_EXISTS(Constants.ALREADY_EXISTS, HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    ResponseStatus(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
