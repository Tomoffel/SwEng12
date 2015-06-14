
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

    private final static QName _GetRequestsResponse_QNAME = new QName("http://integration.shelp.de/", "getRequestsResponse");
    private final static QName _GetUpdatedRequests_QNAME = new QName("http://integration.shelp.de/", "getUpdatedRequests");
    private final static QName _AcceptRequest_QNAME = new QName("http://integration.shelp.de/", "acceptRequest");
    private final static QName _DeleteRequest_QNAME = new QName("http://integration.shelp.de/", "deleteRequest");
    private final static QName _GetRequests_QNAME = new QName("http://integration.shelp.de/", "getRequests");
    private final static QName _CreateRequestResponse_QNAME = new QName("http://integration.shelp.de/", "createRequestResponse");
    private final static QName _GetUpdatedRequestsResponse_QNAME = new QName("http://integration.shelp.de/", "getUpdatedRequestsResponse");
    private final static QName _AcceptRequestResponse_QNAME = new QName("http://integration.shelp.de/", "acceptRequestResponse");
    private final static QName _DeleteRequestResponse_QNAME = new QName("http://integration.shelp.de/", "deleteRequestResponse");
    private final static QName _CreateRequest_QNAME = new QName("http://integration.shelp.de/", "createRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.shelp.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRequests }
     * 
     */
    public GetRequests createGetRequests() {
        return new GetRequests();
    }

    /**
     * Create an instance of {@link DeleteRequest }
     * 
     */
    public DeleteRequest createDeleteRequest() {
        return new DeleteRequest();
    }

    /**
     * Create an instance of {@link AcceptRequest }
     * 
     */
    public AcceptRequest createAcceptRequest() {
        return new AcceptRequest();
    }

    /**
     * Create an instance of {@link GetUpdatedRequests }
     * 
     */
    public GetUpdatedRequests createGetUpdatedRequests() {
        return new GetUpdatedRequests();
    }

    /**
     * Create an instance of {@link GetRequestsResponse }
     * 
     */
    public GetRequestsResponse createGetRequestsResponse() {
        return new GetRequestsResponse();
    }

    /**
     * Create an instance of {@link DeleteRequestResponse }
     * 
     */
    public DeleteRequestResponse createDeleteRequestResponse() {
        return new DeleteRequestResponse();
    }

    /**
     * Create an instance of {@link AcceptRequestResponse }
     * 
     */
    public AcceptRequestResponse createAcceptRequestResponse() {
        return new AcceptRequestResponse();
    }

    /**
     * Create an instance of {@link CreateRequest }
     * 
     */
    public CreateRequest createCreateRequest() {
        return new CreateRequest();
    }

    /**
     * Create an instance of {@link CreateRequestResponse }
     * 
     */
    public CreateRequestResponse createCreateRequestResponse() {
        return new CreateRequestResponse();
    }

    /**
     * Create an instance of {@link GetUpdatedRequestsResponse }
     * 
     */
    public GetUpdatedRequestsResponse createGetUpdatedRequestsResponse() {
        return new GetUpdatedRequestsResponse();
    }

    /**
     * Create an instance of {@link LocationTO }
     * 
     */
    public LocationTO createLocationTO() {
        return new LocationTO();
    }

    /**
     * Create an instance of {@link UserTO }
     * 
     */
    public UserTO createUserTO() {
        return new UserTO();
    }

    /**
     * Create an instance of {@link WishlistItemTO }
     * 
     */
    public WishlistItemTO createWishlistItemTO() {
        return new WishlistItemTO();
    }

    /**
     * Create an instance of {@link TourTO }
     * 
     */
    public TourTO createTourTO() {
        return new TourTO();
    }

    /**
     * Create an instance of {@link ApprovalStatusTO }
     * 
     */
    public ApprovalStatusTO createApprovalStatusTO() {
        return new ApprovalStatusTO();
    }

    /**
     * Create an instance of {@link DeliveryConditionTO }
     * 
     */
    public DeliveryConditionTO createDeliveryConditionTO() {
        return new DeliveryConditionTO();
    }

    /**
     * Create an instance of {@link RequestsResponse }
     * 
     */
    public RequestsResponse createRequestsResponse() {
        return new RequestsResponse();
    }

    /**
     * Create an instance of {@link RequestTO }
     * 
     */
    public RequestTO createRequestTO() {
        return new RequestTO();
    }

    /**
     * Create an instance of {@link CapacityTO }
     * 
     */
    public CapacityTO createCapacityTO() {
        return new CapacityTO();
    }

    /**
     * Create an instance of {@link PaymentConditionTO }
     * 
     */
    public PaymentConditionTO createPaymentConditionTO() {
        return new PaymentConditionTO();
    }

    /**
     * Create an instance of {@link ReturnCodeResponse }
     * 
     */
    public ReturnCodeResponse createReturnCodeResponse() {
        return new ReturnCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getRequestsResponse")
    public JAXBElement<GetRequestsResponse> createGetRequestsResponse(GetRequestsResponse value) {
        return new JAXBElement<GetRequestsResponse>(_GetRequestsResponse_QNAME, GetRequestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUpdatedRequests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getUpdatedRequests")
    public JAXBElement<GetUpdatedRequests> createGetUpdatedRequests(GetUpdatedRequests value) {
        return new JAXBElement<GetUpdatedRequests>(_GetUpdatedRequests_QNAME, GetUpdatedRequests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "acceptRequest")
    public JAXBElement<AcceptRequest> createAcceptRequest(AcceptRequest value) {
        return new JAXBElement<AcceptRequest>(_AcceptRequest_QNAME, AcceptRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deleteRequest")
    public JAXBElement<DeleteRequest> createDeleteRequest(DeleteRequest value) {
        return new JAXBElement<DeleteRequest>(_DeleteRequest_QNAME, DeleteRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getRequests")
    public JAXBElement<GetRequests> createGetRequests(GetRequests value) {
        return new JAXBElement<GetRequests>(_GetRequests_QNAME, GetRequests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "createRequestResponse")
    public JAXBElement<CreateRequestResponse> createCreateRequestResponse(CreateRequestResponse value) {
        return new JAXBElement<CreateRequestResponse>(_CreateRequestResponse_QNAME, CreateRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUpdatedRequestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getUpdatedRequestsResponse")
    public JAXBElement<GetUpdatedRequestsResponse> createGetUpdatedRequestsResponse(GetUpdatedRequestsResponse value) {
        return new JAXBElement<GetUpdatedRequestsResponse>(_GetUpdatedRequestsResponse_QNAME, GetUpdatedRequestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "acceptRequestResponse")
    public JAXBElement<AcceptRequestResponse> createAcceptRequestResponse(AcceptRequestResponse value) {
        return new JAXBElement<AcceptRequestResponse>(_AcceptRequestResponse_QNAME, AcceptRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deleteRequestResponse")
    public JAXBElement<DeleteRequestResponse> createDeleteRequestResponse(DeleteRequestResponse value) {
        return new JAXBElement<DeleteRequestResponse>(_DeleteRequestResponse_QNAME, DeleteRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "createRequest")
    public JAXBElement<CreateRequest> createCreateRequest(CreateRequest value) {
        return new JAXBElement<CreateRequest>(_CreateRequest_QNAME, CreateRequest.class, null, value);
    }

}
