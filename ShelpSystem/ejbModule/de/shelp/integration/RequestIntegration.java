package de.shelp.integration;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.ws.api.annotation.WebContext;

import de.shelp.dto.ReturnCodeResponse;
import de.shelp.dto.request.RequestResponse;
import de.shelp.dto.request.RequestsResponse;

@WebService
@WebContext(contextRoot = "/shelp")
@Stateless
public class RequestIntegration {

    public ReturnCodeResponse acceptRequest(long requestId,
	    List<Integer> acceptedIds) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	return response;
    }

    public ReturnCodeResponse createRequest(String targetUserId, long tourId,
	    List<String> wishes, String notice, String sessionId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	return response;
    }

    public RequestResponse getRequest(long requestId) {
	RequestResponse response = new RequestResponse();

	return response;
    }

    public ReturnCodeResponse deleteRequest(long requestId) {
	ReturnCodeResponse response = new ReturnCodeResponse();

	return response;
    }

    public RequestsResponse updateOwnRequests(int sessionId) {
	RequestsResponse response = new RequestsResponse();

	return response;
    }

}
