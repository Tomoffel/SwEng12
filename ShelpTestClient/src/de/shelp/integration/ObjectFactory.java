
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

    private final static QName _AcceptFriendshipResponse_QNAME = new QName("http://integration.shelp.de/", "acceptFriendshipResponse");
    private final static QName _SessionNotExistException_QNAME = new QName("http://integration.shelp.de/", "SessionNotExistException");
    private final static QName _GetFriendsResponse_QNAME = new QName("http://integration.shelp.de/", "getFriendsResponse");
    private final static QName _DeniedFriendshipResponse_QNAME = new QName("http://integration.shelp.de/", "deniedFriendshipResponse");
    private final static QName _AddFriend_QNAME = new QName("http://integration.shelp.de/", "addFriend");
    private final static QName _DeleteFriendshipResponse_QNAME = new QName("http://integration.shelp.de/", "deleteFriendshipResponse");
    private final static QName _AddFriendResponse_QNAME = new QName("http://integration.shelp.de/", "addFriendResponse");
    private final static QName _GetFriends_QNAME = new QName("http://integration.shelp.de/", "getFriends");
    private final static QName _DeleteFriendship_QNAME = new QName("http://integration.shelp.de/", "deleteFriendship");
    private final static QName _DeniedFriendship_QNAME = new QName("http://integration.shelp.de/", "deniedFriendship");
    private final static QName _AcceptFriendship_QNAME = new QName("http://integration.shelp.de/", "acceptFriendship");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.shelp.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFriendsResponse }
     * 
     */
    public GetFriendsResponse createGetFriendsResponse() {
        return new GetFriendsResponse();
    }

    /**
     * Create an instance of {@link DeniedFriendshipResponse }
     * 
     */
    public DeniedFriendshipResponse createDeniedFriendshipResponse() {
        return new DeniedFriendshipResponse();
    }

    /**
     * Create an instance of {@link AcceptFriendshipResponse }
     * 
     */
    public AcceptFriendshipResponse createAcceptFriendshipResponse() {
        return new AcceptFriendshipResponse();
    }

    /**
     * Create an instance of {@link SessionNotExistException }
     * 
     */
    public SessionNotExistException createSessionNotExistException() {
        return new SessionNotExistException();
    }

    /**
     * Create an instance of {@link GetFriends }
     * 
     */
    public GetFriends createGetFriends() {
        return new GetFriends();
    }

    /**
     * Create an instance of {@link DeniedFriendship }
     * 
     */
    public DeniedFriendship createDeniedFriendship() {
        return new DeniedFriendship();
    }

    /**
     * Create an instance of {@link DeleteFriendship }
     * 
     */
    public DeleteFriendship createDeleteFriendship() {
        return new DeleteFriendship();
    }

    /**
     * Create an instance of {@link AcceptFriendship }
     * 
     */
    public AcceptFriendship createAcceptFriendship() {
        return new AcceptFriendship();
    }

    /**
     * Create an instance of {@link DeleteFriendshipResponse }
     * 
     */
    public DeleteFriendshipResponse createDeleteFriendshipResponse() {
        return new DeleteFriendshipResponse();
    }

    /**
     * Create an instance of {@link AddFriend }
     * 
     */
    public AddFriend createAddFriend() {
        return new AddFriend();
    }

    /**
     * Create an instance of {@link AddFriendResponse }
     * 
     */
    public AddFriendResponse createAddFriendResponse() {
        return new AddFriendResponse();
    }

    /**
     * Create an instance of {@link UserTO }
     * 
     */
    public UserTO createUserTO() {
        return new UserTO();
    }

    /**
     * Create an instance of {@link FriendshipTO }
     * 
     */
    public FriendshipTO createFriendshipTO() {
        return new FriendshipTO();
    }

    /**
     * Create an instance of {@link FriendsResponse }
     * 
     */
    public FriendsResponse createFriendsResponse() {
        return new FriendsResponse();
    }

    /**
     * Create an instance of {@link ReturnCodeResponse }
     * 
     */
    public ReturnCodeResponse createReturnCodeResponse() {
        return new ReturnCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptFriendshipResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "acceptFriendshipResponse")
    public JAXBElement<AcceptFriendshipResponse> createAcceptFriendshipResponse(AcceptFriendshipResponse value) {
        return new JAXBElement<AcceptFriendshipResponse>(_AcceptFriendshipResponse_QNAME, AcceptFriendshipResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionNotExistException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "SessionNotExistException")
    public JAXBElement<SessionNotExistException> createSessionNotExistException(SessionNotExistException value) {
        return new JAXBElement<SessionNotExistException>(_SessionNotExistException_QNAME, SessionNotExistException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFriendsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getFriendsResponse")
    public JAXBElement<GetFriendsResponse> createGetFriendsResponse(GetFriendsResponse value) {
        return new JAXBElement<GetFriendsResponse>(_GetFriendsResponse_QNAME, GetFriendsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeniedFriendshipResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deniedFriendshipResponse")
    public JAXBElement<DeniedFriendshipResponse> createDeniedFriendshipResponse(DeniedFriendshipResponse value) {
        return new JAXBElement<DeniedFriendshipResponse>(_DeniedFriendshipResponse_QNAME, DeniedFriendshipResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFriend }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "addFriend")
    public JAXBElement<AddFriend> createAddFriend(AddFriend value) {
        return new JAXBElement<AddFriend>(_AddFriend_QNAME, AddFriend.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFriendshipResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deleteFriendshipResponse")
    public JAXBElement<DeleteFriendshipResponse> createDeleteFriendshipResponse(DeleteFriendshipResponse value) {
        return new JAXBElement<DeleteFriendshipResponse>(_DeleteFriendshipResponse_QNAME, DeleteFriendshipResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFriendResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "addFriendResponse")
    public JAXBElement<AddFriendResponse> createAddFriendResponse(AddFriendResponse value) {
        return new JAXBElement<AddFriendResponse>(_AddFriendResponse_QNAME, AddFriendResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFriends }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "getFriends")
    public JAXBElement<GetFriends> createGetFriends(GetFriends value) {
        return new JAXBElement<GetFriends>(_GetFriends_QNAME, GetFriends.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFriendship }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deleteFriendship")
    public JAXBElement<DeleteFriendship> createDeleteFriendship(DeleteFriendship value) {
        return new JAXBElement<DeleteFriendship>(_DeleteFriendship_QNAME, DeleteFriendship.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeniedFriendship }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "deniedFriendship")
    public JAXBElement<DeniedFriendship> createDeniedFriendship(DeniedFriendship value) {
        return new JAXBElement<DeniedFriendship>(_DeniedFriendship_QNAME, DeniedFriendship.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptFriendship }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "acceptFriendship")
    public JAXBElement<AcceptFriendship> createAcceptFriendship(AcceptFriendship value) {
        return new JAXBElement<AcceptFriendship>(_AcceptFriendship_QNAME, AcceptFriendship.class, null, value);
    }

}
