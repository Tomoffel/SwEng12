
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

    private final static QName _CreateTourResponse_QNAME = new QName("http://integration.shelp.de/", "createTourResponse");
    private final static QName _SearchToursResponse_QNAME = new QName("http://integration.shelp.de/", "searchToursResponse");
    private final static QName _DeleteTourResponse_QNAME = new QName("http://integration.shelp.de/", "deleteTourResponse");
    private final static QName _CreateTour_QNAME = new QName("http://integration.shelp.de/", "createTour");
    private final static QName _GetRequestsOfTourResponse_QNAME = new QName("http://integration.shelp.de/", "getRequestsOfTourResponse");
    private final static QName _GetToursResponse_QNAME = new QName("http://integration.shelp.de/", "getToursResponse");
    private final static QName _GetUpdatedTours_QNAME = new QName("http://integration.shelp.de/", "getUpdatedTours");
    private final static QName _DeleteTour_QNAME = new QName("http://integration.shelp.de/", "deleteTour");
    private final static QName _SearchTours_QNAME = new QName("http://integration.shelp.de/", "searchTours");
    private final static QName _GetTours_QNAME = new QName("http://integration.shelp.de/", "getTours");
    private final static QName _GetUpdatedToursResponse_QNAME = new QName("http://integration.shelp.de/", "getUpdatedToursResponse");
    private final static QName _GetRequestsOfTour_QNAME = new QName("http://integration.shelp.de/", "getRequestsOfTour");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.shelp.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUpdatedTours }
     * 
     */
    public GetUpdatedTours createGetUpdatedTours() {
        return new GetUpdatedTours();
    }

    /**
     * Create an instance of {@link DeleteTour }
     * 
     */
    public DeleteTour createDeleteTour() {
        return new DeleteTour();
    }

    /**
     * Create an instance of {@link SearchToursResponse }
     * 
     */
    public SearchToursResponse createSearchToursResponse() {
        return new SearchToursResponse();
    }

    /**
     * Create an instance of {@link CreateTourResponse }
     * 
     */
    public CreateTourResponse createCreateTourResponse() {
        return new CreateTourResponse();
    }

    /**
     * Create an instance of {@link DeleteTourResponse }
     * 
     */
    public DeleteTourResponse createDeleteTourResponse() {
        return new DeleteTourResponse();
    }

    /**
     * Create an instance of {@link GetToursResponse }
     * 
     */
    public GetToursResponse createGetToursResponse() {
        return new GetToursResponse();
    }

    /**
     * Create an instance of {@link GetRequestsOfTourResponse }
     * 
     */
    public GetRequestsOfTourResponse createGetRequestsOfTourResponse() {
        return new GetRequestsOfTourResponse();
    }

    /**
     * Create an instance of {@link CreateTour }
     * 
     */
    public CreateTour createCreateTour() {
        return new CreateTour();
    }

    /**
     * Create an instance of {@link GetUpdatedToursResponse }
     * 
     */
    public GetUpdatedToursResponse createGetUpdatedToursResponse() {
        return new GetUpdatedToursResponse();
    }

    /**
     * Create an instance of {@link GetRequestsOfTour }
     * 
     */
    public GetRequestsOfTour createGetRequestsOfTour() {
        return new GetRequestsOfTour();
    }

    /**
     * Create an instance of {@link SearchTours }
     * 
     */
    public SearchTours createSearchTours() {
        return new SearchTours();
    }

    /**
     * Create an instance of {@link GetTours }
     * 
     */
    public GetTours createGetTours() {
        return new GetTours();
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
     * Create an instance of {@link ToursResponse }
     * 
     */
    public ToursResponse createToursResponse() {
        return new ToursResponse();
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
     * Create an instance of {@link RequestTO }
     * 
     */
    public RequestTO createRequestTO() {
        return new RequestTO();
    }

    /**
     * Create an instance of {@link RequestsResponse }
     * 
     */
    public RequestsResponse createRequestsResponse() {
        return new RequestsResponse();
    }

    /**
     * Create an instance of {@link CapacityTO }
     * 
     */
    public CapacityTO createCapacityTO() {
        return new CapacityTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTourResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "createTourResponse")
    public JAXBElement<CreateTourResponse> createCreateTourResponse(CreateTourResponse value) {
        return new JAXBElement<CreateTourResponse>(_CreateTourResponse_QNAME, CreateTourResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchToursResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "searchToursResponse")
    public JAXBElement<SearchToursResponse> createSearchToursResponse(SearchToursResponse value) {
        return new JAXBElement<SearchToursResponse>(_SearchToursResponse_QNAME, SearchToursResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTourResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deleteTourResponse")
    public JAXBElement<DeleteTourResponse> createDeleteTourResponse(DeleteTourResponse value) {
        return new JAXBElement<DeleteTourResponse>(_DeleteTourResponse_QNAME, DeleteTourResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTour }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "createTour")
    public JAXBElement<CreateTour> createCreateTour(CreateTour value) {
        return new JAXBElement<CreateTour>(_CreateTour_QNAME, CreateTour.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestsOfTourResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getRequestsOfTourResponse")
    public JAXBElement<GetRequestsOfTourResponse> createGetRequestsOfTourResponse(GetRequestsOfTourResponse value) {
        return new JAXBElement<GetRequestsOfTourResponse>(_GetRequestsOfTourResponse_QNAME, GetRequestsOfTourResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetToursResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getToursResponse")
    public JAXBElement<GetToursResponse> createGetToursResponse(GetToursResponse value) {
        return new JAXBElement<GetToursResponse>(_GetToursResponse_QNAME, GetToursResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUpdatedTours }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getUpdatedTours")
    public JAXBElement<GetUpdatedTours> createGetUpdatedTours(GetUpdatedTours value) {
        return new JAXBElement<GetUpdatedTours>(_GetUpdatedTours_QNAME, GetUpdatedTours.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTour }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deleteTour")
    public JAXBElement<DeleteTour> createDeleteTour(DeleteTour value) {
        return new JAXBElement<DeleteTour>(_DeleteTour_QNAME, DeleteTour.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTours }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "searchTours")
    public JAXBElement<SearchTours> createSearchTours(SearchTours value) {
        return new JAXBElement<SearchTours>(_SearchTours_QNAME, SearchTours.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTours }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getTours")
    public JAXBElement<GetTours> createGetTours(GetTours value) {
        return new JAXBElement<GetTours>(_GetTours_QNAME, GetTours.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUpdatedToursResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getUpdatedToursResponse")
    public JAXBElement<GetUpdatedToursResponse> createGetUpdatedToursResponse(GetUpdatedToursResponse value) {
        return new JAXBElement<GetUpdatedToursResponse>(_GetUpdatedToursResponse_QNAME, GetUpdatedToursResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestsOfTour }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getRequestsOfTour")
    public JAXBElement<GetRequestsOfTour> createGetRequestsOfTour(GetRequestsOfTour value) {
        return new JAXBElement<GetRequestsOfTour>(_GetRequestsOfTour_QNAME, GetRequestsOfTour.class, null, value);
    }

}
