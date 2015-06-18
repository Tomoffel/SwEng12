
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

    private final static QName _CreateRating_QNAME = new QName("http://integration.shelp.de/", "createRating");
    private final static QName _GetRatings_QNAME = new QName("http://integration.shelp.de/", "getRatings");
    private final static QName _CreateRatingResponse_QNAME = new QName("http://integration.shelp.de/", "createRatingResponse");
    private final static QName _GetRatingsResponse_QNAME = new QName("http://integration.shelp.de/", "getRatingsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.shelp.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRatings }
     * 
     */
    public GetRatings createGetRatings() {
        return new GetRatings();
    }

    /**
     * Create an instance of {@link CreateRatingResponse }
     * 
     */
    public CreateRatingResponse createCreateRatingResponse() {
        return new CreateRatingResponse();
    }

    /**
     * Create an instance of {@link CreateRating }
     * 
     */
    public CreateRating createCreateRating() {
        return new CreateRating();
    }

    /**
     * Create an instance of {@link GetRatingsResponse }
     * 
     */
    public GetRatingsResponse createGetRatingsResponse() {
        return new GetRatingsResponse();
    }

    /**
     * Create an instance of {@link RatingTO }
     * 
     */
    public RatingTO createRatingTO() {
        return new RatingTO();
    }

    /**
     * Create an instance of {@link UserTO }
     * 
     */
    public UserTO createUserTO() {
        return new UserTO();
    }

    /**
     * Create an instance of {@link RatingResponse }
     * 
     */
    public RatingResponse createRatingResponse() {
        return new RatingResponse();
    }

    /**
     * Create an instance of {@link ReturnCodeResponse }
     * 
     */
    public ReturnCodeResponse createReturnCodeResponse() {
        return new ReturnCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRating }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "createRating")
    public JAXBElement<CreateRating> createCreateRating(CreateRating value) {
        return new JAXBElement<CreateRating>(_CreateRating_QNAME, CreateRating.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRatings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getRatings")
    public JAXBElement<GetRatings> createGetRatings(GetRatings value) {
        return new JAXBElement<GetRatings>(_GetRatings_QNAME, GetRatings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRatingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "createRatingResponse")
    public JAXBElement<CreateRatingResponse> createCreateRatingResponse(CreateRatingResponse value) {
        return new JAXBElement<CreateRatingResponse>(_CreateRatingResponse_QNAME, CreateRatingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRatingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getRatingsResponse")
    public JAXBElement<GetRatingsResponse> createGetRatingsResponse(GetRatingsResponse value) {
        return new JAXBElement<GetRatingsResponse>(_GetRatingsResponse_QNAME, GetRatingsResponse.class, null, value);
    }

}
