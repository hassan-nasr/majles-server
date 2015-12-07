package ir.hassannasr.majles.core.error;

import core.base.constants.Constants;
import core.model.ActionLog;

import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
* Created by abolfazl on 3/31/14.
*/
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(final Throwable exception) {
        exception.printStackTrace();

        ActionLog log = (ActionLog) request.getAttribute(Constants.ACTION_LOG_REQUEST_KEY);

        if (exception instanceof OptimisticLockException) {
            ErrorInfo errorInfo = ErrorInfoFactory.createAndLogError(Response.Status.PRECONDITION_FAILED, "این شکل/سطر/این‌همانی در جای دیگری تغییر کرده است!");
            log.setFailureCode(Response.Status.PRECONDITION_FAILED.getStatusCode());
            return Response.status(errorInfo.getStatus()).entity(errorInfo).type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        ErrorInfo errorInfo = ErrorInfoFactory.createAndLogError(Response.Status.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.setFailureCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        return Response.status(errorInfo.getStatus()).entity(errorInfo).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}