
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

    private final static QName _LoginResponse_QNAME = new QName("http://integration.shelp.de/", "loginResponse");
    private final static QName _RegUserResponse_QNAME = new QName("http://integration.shelp.de/", "regUserResponse");
    private final static QName _SearchUsersResponse_QNAME = new QName("http://integration.shelp.de/", "searchUsersResponse");
    private final static QName _Logout_QNAME = new QName("http://integration.shelp.de/", "logout");
    private final static QName _LogoutResponse_QNAME = new QName("http://integration.shelp.de/", "logoutResponse");
    private final static QName _RegUser_QNAME = new QName("http://integration.shelp.de/", "regUser");
    private final static QName _SearchUsers_QNAME = new QName("http://integration.shelp.de/", "searchUsers");
    private final static QName _Login_QNAME = new QName("http://integration.shelp.de/", "login");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.shelp.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchUsersResponse }
     * 
     */
    public SearchUsersResponse createSearchUsersResponse() {
        return new SearchUsersResponse();
    }

    /**
     * Create an instance of {@link RegUserResponse }
     * 
     */
    public RegUserResponse createRegUserResponse() {
        return new RegUserResponse();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link RegUser }
     * 
     */
    public RegUser createRegUser() {
        return new RegUser();
    }

    /**
     * Create an instance of {@link SearchUsers }
     * 
     */
    public SearchUsers createSearchUsers() {
        return new SearchUsers();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link UserResponse }
     * 
     */
    public UserResponse createUserResponse() {
        return new UserResponse();
    }

    /**
     * Create an instance of {@link ShelpSessionTO }
     * 
     */
    public ShelpSessionTO createShelpSessionTO() {
        return new ShelpSessionTO();
    }

    /**
     * Create an instance of {@link UserTO }
     * 
     */
    public UserTO createUserTO() {
        return new UserTO();
    }

    /**
     * Create an instance of {@link ReturnCodeResponse }
     * 
     */
    public ReturnCodeResponse createReturnCodeResponse() {
        return new ReturnCodeResponse();
    }

    /**
     * Create an instance of {@link UsersResponse }
     * 
     */
    public UsersResponse createUsersResponse() {
        return new UsersResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "regUserResponse")
    public JAXBElement<RegUserResponse> createRegUserResponse(RegUserResponse value) {
        return new JAXBElement<RegUserResponse>(_RegUserResponse_QNAME, RegUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "searchUsersResponse")
    public JAXBElement<SearchUsersResponse> createSearchUsersResponse(SearchUsersResponse value) {
        return new JAXBElement<SearchUsersResponse>(_SearchUsersResponse_QNAME, SearchUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "regUser")
    public JAXBElement<RegUser> createRegUser(RegUser value) {
        return new JAXBElement<RegUser>(_RegUser_QNAME, RegUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "searchUsers")
    public JAXBElement<SearchUsers> createSearchUsers(SearchUsers value) {
        return new JAXBElement<SearchUsers>(_SearchUsers_QNAME, SearchUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integration.shelp.de/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

}
