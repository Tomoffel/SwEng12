package de.shelp.integration;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.13
 * 2015-06-14T09:32:09.953+02:00
 * Generated source version: 2.7.13
 * 
 */
@WebService(targetNamespace = "http://integration.shelp.de/", name = "RequestIntegration")
@XmlSeeAlso({ObjectFactory.class})
public interface RequestIntegration {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "createRequest", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.CreateRequest")
    @WebMethod
    @ResponseWrapper(localName = "createRequestResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.CreateRequestResponse")
    public de.shelp.integration.ReturnCodeResponse createRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "acceptRequest", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.AcceptRequest")
    @WebMethod
    @ResponseWrapper(localName = "acceptRequestResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.AcceptRequestResponse")
    public de.shelp.integration.ReturnCodeResponse acceptRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getUpdatedRequests", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetUpdatedRequests")
    @WebMethod
    @ResponseWrapper(localName = "getUpdatedRequestsResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetUpdatedRequestsResponse")
    public de.shelp.integration.RequestsResponse getUpdatedRequests(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        long arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deleteRequest", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.DeleteRequest")
    @WebMethod
    @ResponseWrapper(localName = "deleteRequestResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.DeleteRequestResponse")
    public de.shelp.integration.ReturnCodeResponse deleteRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getRequests", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetRequests")
    @WebMethod
    @ResponseWrapper(localName = "getRequestsResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetRequestsResponse")
    public de.shelp.integration.RequestsResponse getRequests(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0
    );
}
