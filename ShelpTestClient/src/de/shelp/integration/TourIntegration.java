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
 * 2015-05-12T12:48:46.019+02:00
 * Generated source version: 2.7.13
 * 
 */
@WebService(targetNamespace = "http://integration.shelp.de/", name = "TourIntegration")
@XmlSeeAlso({ObjectFactory.class})
public interface TourIntegration {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getUpdatedTours", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetUpdatedTours")
    @WebMethod
    @ResponseWrapper(localName = "getUpdatedToursResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetUpdatedToursResponse")
    public de.shelp.integration.ToursResponse getUpdatedTours(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "searchTour", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.SearchTour")
    @WebMethod
    @ResponseWrapper(localName = "searchTourResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.SearchTourResponse")
    public de.shelp.integration.ToursResponse searchTour(
        @WebParam(name = "arg0", targetNamespace = "")
        de.shelp.integration.ApprovalStatus arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        de.shelp.integration.LocationTO arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        boolean arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        int arg5
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getRequestsOfTour", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetRequestsOfTour")
    @WebMethod
    @ResponseWrapper(localName = "getRequestsOfTourResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetRequestsOfTourResponse")
    public de.shelp.integration.RequestsResponse getRequestsOfTour(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deleteTour", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.DeleteTour")
    @WebMethod
    @ResponseWrapper(localName = "deleteTourResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.DeleteTourResponse")
    public de.shelp.integration.ReturnCodeResponse deleteTour(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "createTour", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.CreateTour")
    @WebMethod
    @ResponseWrapper(localName = "createTourResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.CreateTourResponse")
    public de.shelp.integration.ReturnCodeResponse createTour(
        @WebParam(name = "arg0", targetNamespace = "")
        de.shelp.integration.TourTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getTour", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetTour")
    @WebMethod
    @ResponseWrapper(localName = "getTourResponse", targetNamespace = "http://integration.shelp.de/", className = "de.shelp.integration.GetTourResponse")
    public de.shelp.integration.TourResponse getTour(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );
}
