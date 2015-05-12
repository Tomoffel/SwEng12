
package de.shelp.integration;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.shelp.integration package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetApprovalStatusResponse_QNAME = new QName("http://integration.shelp.de/", "getApprovalStatusResponse");
    private final static QName _GetPaymentConditions_QNAME = new QName("http://integration.shelp.de/", "getPaymentConditions");
    private final static QName _GetLocationsResponse_QNAME = new QName("http://integration.shelp.de/", "getLocationsResponse");
    private final static QName _GetAllLists_QNAME = new QName("http://integration.shelp.de/", "getAllLists");
    private final static QName _GetLocations_QNAME = new QName("http://integration.shelp.de/", "getLocations");
    private final static QName _GetCapacitiesResponse_QNAME = new QName("http://integration.shelp.de/", "getCapacitiesResponse");
    private final static QName _GetCapacities_QNAME = new QName("http://integration.shelp.de/", "getCapacities");
    private final static QName _GetDeliveryConditionsResponse_QNAME = new QName("http://integration.shelp.de/", "getDeliveryConditionsResponse");
    private final static QName _GetPaymentConditionsResponse_QNAME = new QName("http://integration.shelp.de/", "getPaymentConditionsResponse");
    private final static QName _GetApprovalStatus_QNAME = new QName("http://integration.shelp.de/", "getApprovalStatus");
    private final static QName _GetDeliveryConditions_QNAME = new QName("http://integration.shelp.de/", "getDeliveryConditions");
    private final static QName _GetAllListsResponse_QNAME = new QName("http://integration.shelp.de/", "getAllListsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.shelp.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPaymentConditions }
     * 
     */
    public GetPaymentConditions createGetPaymentConditions() {
        return new GetPaymentConditions();
    }

    /**
     * Create an instance of {@link GetApprovalStatusResponse }
     * 
     */
    public GetApprovalStatusResponse createGetApprovalStatusResponse() {
        return new GetApprovalStatusResponse();
    }

    /**
     * Create an instance of {@link GetLocationsResponse }
     * 
     */
    public GetLocationsResponse createGetLocationsResponse() {
        return new GetLocationsResponse();
    }

    /**
     * Create an instance of {@link GetLocations }
     * 
     */
    public GetLocations createGetLocations() {
        return new GetLocations();
    }

    /**
     * Create an instance of {@link GetAllLists }
     * 
     */
    public GetAllLists createGetAllLists() {
        return new GetAllLists();
    }

    /**
     * Create an instance of {@link GetCapacitiesResponse }
     * 
     */
    public GetCapacitiesResponse createGetCapacitiesResponse() {
        return new GetCapacitiesResponse();
    }

    /**
     * Create an instance of {@link GetDeliveryConditions }
     * 
     */
    public GetDeliveryConditions createGetDeliveryConditions() {
        return new GetDeliveryConditions();
    }

    /**
     * Create an instance of {@link GetAllListsResponse }
     * 
     */
    public GetAllListsResponse createGetAllListsResponse() {
        return new GetAllListsResponse();
    }

    /**
     * Create an instance of {@link GetPaymentConditionsResponse }
     * 
     */
    public GetPaymentConditionsResponse createGetPaymentConditionsResponse() {
        return new GetPaymentConditionsResponse();
    }

    /**
     * Create an instance of {@link GetDeliveryConditionsResponse }
     * 
     */
    public GetDeliveryConditionsResponse createGetDeliveryConditionsResponse() {
        return new GetDeliveryConditionsResponse();
    }

    /**
     * Create an instance of {@link GetCapacities }
     * 
     */
    public GetCapacities createGetCapacities() {
        return new GetCapacities();
    }

    /**
     * Create an instance of {@link GetApprovalStatus }
     * 
     */
    public GetApprovalStatus createGetApprovalStatus() {
        return new GetApprovalStatus();
    }

    /**
     * Create an instance of {@link LocationResponse }
     * 
     */
    public LocationResponse createLocationResponse() {
        return new LocationResponse();
    }

    /**
     * Create an instance of {@link LocationTO }
     * 
     */
    public LocationTO createLocationTO() {
        return new LocationTO();
    }

    /**
     * Create an instance of {@link CapacitiesResponse }
     * 
     */
    public CapacitiesResponse createCapacitiesResponse() {
        return new CapacitiesResponse();
    }

    /**
     * Create an instance of {@link ApprovalStatusResponse }
     * 
     */
    public ApprovalStatusResponse createApprovalStatusResponse() {
        return new ApprovalStatusResponse();
    }

    /**
     * Create an instance of {@link PaymentConditionsResponse }
     * 
     */
    public PaymentConditionsResponse createPaymentConditionsResponse() {
        return new PaymentConditionsResponse();
    }

    /**
     * Create an instance of {@link DeliveryConditionResponse }
     * 
     */
    public DeliveryConditionResponse createDeliveryConditionResponse() {
        return new DeliveryConditionResponse();
    }

    /**
     * Create an instance of {@link AllListResponse }
     * 
     */
    public AllListResponse createAllListResponse() {
        return new AllListResponse();
    }

    /**
     * Create an instance of {@link ReturnCodeResponse }
     * 
     */
    public ReturnCodeResponse createReturnCodeResponse() {
        return new ReturnCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetApprovalStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getApprovalStatusResponse")
    public JAXBElement<GetApprovalStatusResponse> createGetApprovalStatusResponse(GetApprovalStatusResponse value) {
        return new JAXBElement<GetApprovalStatusResponse>(_GetApprovalStatusResponse_QNAME, GetApprovalStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentConditions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getPaymentConditions")
    public JAXBElement<GetPaymentConditions> createGetPaymentConditions(GetPaymentConditions value) {
        return new JAXBElement<GetPaymentConditions>(_GetPaymentConditions_QNAME, GetPaymentConditions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getLocationsResponse")
    public JAXBElement<GetLocationsResponse> createGetLocationsResponse(GetLocationsResponse value) {
        return new JAXBElement<GetLocationsResponse>(_GetLocationsResponse_QNAME, GetLocationsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getAllLists")
    public JAXBElement<GetAllLists> createGetAllLists(GetAllLists value) {
        return new JAXBElement<GetAllLists>(_GetAllLists_QNAME, GetAllLists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getLocations")
    public JAXBElement<GetLocations> createGetLocations(GetLocations value) {
        return new JAXBElement<GetLocations>(_GetLocations_QNAME, GetLocations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCapacitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getCapacitiesResponse")
    public JAXBElement<GetCapacitiesResponse> createGetCapacitiesResponse(GetCapacitiesResponse value) {
        return new JAXBElement<GetCapacitiesResponse>(_GetCapacitiesResponse_QNAME, GetCapacitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCapacities }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getCapacities")
    public JAXBElement<GetCapacities> createGetCapacities(GetCapacities value) {
        return new JAXBElement<GetCapacities>(_GetCapacities_QNAME, GetCapacities.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeliveryConditionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getDeliveryConditionsResponse")
    public JAXBElement<GetDeliveryConditionsResponse> createGetDeliveryConditionsResponse(GetDeliveryConditionsResponse value) {
        return new JAXBElement<GetDeliveryConditionsResponse>(_GetDeliveryConditionsResponse_QNAME, GetDeliveryConditionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentConditionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getPaymentConditionsResponse")
    public JAXBElement<GetPaymentConditionsResponse> createGetPaymentConditionsResponse(GetPaymentConditionsResponse value) {
        return new JAXBElement<GetPaymentConditionsResponse>(_GetPaymentConditionsResponse_QNAME, GetPaymentConditionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetApprovalStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getApprovalStatus")
    public JAXBElement<GetApprovalStatus> createGetApprovalStatus(GetApprovalStatus value) {
        return new JAXBElement<GetApprovalStatus>(_GetApprovalStatus_QNAME, GetApprovalStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeliveryConditions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getDeliveryConditions")
    public JAXBElement<GetDeliveryConditions> createGetDeliveryConditions(GetDeliveryConditions value) {
        return new JAXBElement<GetDeliveryConditions>(_GetDeliveryConditions_QNAME, GetDeliveryConditions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllListsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getAllListsResponse")
    public JAXBElement<GetAllListsResponse> createGetAllListsResponse(GetAllListsResponse value) {
        return new JAXBElement<GetAllListsResponse>(_GetAllListsResponse_QNAME, GetAllListsResponse.class, null, value);
    }

}
