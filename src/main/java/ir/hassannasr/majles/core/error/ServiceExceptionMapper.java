package ir.hassannasr.majles.core.error;

import core.base.constants.Constants;
import core.model.ActionLog;

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
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(final ServiceException exception) {
        Response.Status status = exception.getStatus();
        if (status == null) {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        ErrorInfo errorInfo = ErrorInfoFactory.createAndLogError(status, exception.getMessage());
        ((ActionLog)request.getAttribute(Constants.ACTION_LOG_REQUEST_KEY)).setFailureCode(status.getStatusCode());
        return Response.status(errorInfo.getStatus()).entity(errorInfo).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}