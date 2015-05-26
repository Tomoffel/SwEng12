
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
    private final static QName _GetTourResponse_QNAME = new QName("http://integration.shelp.de/", "getTourResponse");
    private final static QName _DeleteTourResponse_QNAME = new QName("http://integration.shelp.de/", "deleteTourResponse");
    private final static QName _CreateTour_QNAME = new QName("http://integration.shelp.de/", "createTour");
    private final static QName _GetRequestsOfTourResponse_QNAME = new QName("http://integration.shelp.de/", "getRequestsOfTourResponse");
    private final static QName _SearchTour_QNAME = new QName("http://integration.shelp.de/", "searchTour");
    private final static QName _SearchTourResponse_QNAME = new QName("http://integration.shelp.de/", "searchTourResponse");
    private final static QName _GetUpdatedTours_QNAME = new QName("http://integration.shelp.de/", "getUpdatedTours");
    private final static QName _DeleteTour_QNAME = new QName("http://integration.shelp.de/", "deleteTour");
    private final static QName _GetTour_QNAME = new QName("http://integration.shelp.de/", "getTour");
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
     * Create an instance of {@link GetTour }
     * 
     */
    public GetTour createGetTour() {
        return new GetTour();
    }

    /**
     * Create an instance of {@link DeleteTour }
     * 
     */
    public DeleteTour createDeleteTour() {
        return new DeleteTour();
    }

    /**
     * Create an instance of {@link GetTourResponse }
     * 
     */
    public GetTourResponse createGetTourResponse() {
        return new GetTourResponse();
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
     * Create an instance of {@link SearchTourResponse }
     * 
     */
    public SearchTourResponse createSearchTourResponse() {
        return new SearchTourResponse();
    }

    /**
     * Create an instance of {@link SearchTour }
     * 
     */
    public SearchTour createSearchTour() {
        return new SearchTour();
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
     * Create an instance of {@link LocationTO }
     * 
     */
    public LocationTO createLocationTO() {
        return new LocationTO();
    }

    /**
     * Create an instance of {@link WishlistTO }
     * 
     */
    public WishlistTO createWishlistTO() {
        return new WishlistTO();
    }

    /**
     * Create an instance of {@link UserTO }
     * 
     */
    public UserTO createUserTO() {
        return new UserTO();
    }

    /**
     * Create an instance of {@link TourTO }
     * 
     */
    public TourTO createTourTO() {
        return new TourTO();
    }

    /**
     * Create an instance of {@link ToursResponse }
     * 
     */
    public ToursResponse createToursResponse() {
        return new ToursResponse();
    }

    /**
     * Create an instance of {@link ReturnCodeResponse }
     * 
     */
    public ReturnCodeResponse createReturnCodeResponse() {
        return new ReturnCodeResponse();
    }

    /**
     * Create an instance of {@link TourResponse }
     * 
     */
    public TourResponse createTourResponse() {
        return new TourResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTourResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "createTourResponse")
    public JAXBElement<CreateTourResponse> createCreateTourResponse(CreateTourResponse value) {
        return new JAXBElement<CreateTourResponse>(_CreateTourResponse_QNAME, CreateTourResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTourResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getTourResponse")
    public JAXBElement<GetTourResponse> createGetTourResponse(GetTourResponse value) {
        return new JAXBElement<GetTourResponse>(_GetTourResponse_QNAME, GetTourResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTour }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "searchTour")
    public JAXBElement<SearchTour> createSearchTour(SearchTour value) {
        return new JAXBElement<SearchTour>(_SearchTour_QNAME, SearchTour.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTourResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "searchTourResponse")
    public JAXBElement<SearchTourResponse> createSearchTourResponse(SearchTourResponse value) {
        return new JAXBElement<SearchTourResponse>(_SearchTourResponse_QNAME, SearchTourResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTour }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getTour")
    public JAXBElement<GetTour> createGetTour(GetTour value) {
        return new JAXBElement<GetTour>(_GetTour_QNAME, GetTour.class, null, value);
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
