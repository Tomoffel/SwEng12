
package de.shelp.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für requestTO complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="requestTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="notice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceUser" type="{http://integration.shelp.de/}userTO" minOccurs="0"/>
 *         &lt;element name="status" type="{http://integration.shelp.de/}requestStatus" minOccurs="0"/>
 *         &lt;element name="targetUser" type="{http://integration.shelp.de/}userTO" minOccurs="0"/>
 *         &lt;element name="tour" type="{http://integration.shelp.de/}tourTO" minOccurs="0"/>
 *         &lt;element name="updatedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="wishlist" type="{http://integration.shelp.de/}wishlistTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestTO", propOrder = {
    "id",
    "notice",
    "sourceUser",
    "status",
    "targetUser",
    "tour",
    "updatedOn",
    "wishlist"
})
public class RequestTO {

    protected long id;
    protected String notice;
    protected UserTO sourceUser;
    protected RequestStatus status;
    protected UserTO targetUser;
    protected TourTO tour;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedOn;
    protected WishlistTO wishlist;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der notice-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotice() {
        return notice;
    }

    /**
     * Legt den Wert der notice-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotice(String value) {
        this.notice = value;
    }

    /**
     * Ruft den Wert der sourceUser-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link UserTO }
     *     
     */
    public UserTO getSourceUser() {
        return sourceUser;
    }

    /**
     * Legt den Wert der sourceUser-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link UserTO }
     *     
     */
    public void setSourceUser(UserTO value) {
        this.sourceUser = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RequestStatus }
     *     
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestStatus }
     *     
     */
    public void setStatus(RequestStatus value) {
        this.status = value;
    }

    /**
     * Ruft den Wert der targetUser-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link UserTO }
     *     
     */
    public UserTO getTargetUser() {
        return targetUser;
    }

    /**
     * Legt den Wert der targetUser-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link UserTO }
     *     
     */
    public void setTargetUser(UserTO value) {
        this.targetUser = value;
    }

    /**
     * Ruft den Wert der tour-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TourTO }
     *     
     */
    public TourTO getTour() {
        return tour;
    }

    /**
     * Legt den Wert der tour-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TourTO }
     *     
     */
    public void setTour(TourTO value) {
        this.tour = value;
    }

    /**
     * Ruft den Wert der updatedOn-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedOn() {
        return updatedOn;
    }

    /**
     * Legt den Wert der updatedOn-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedOn(XMLGregorianCalendar value) {
        this.updatedOn = value;
    }

    /**
     * Ruft den Wert der wishlist-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link WishlistTO }
     *     
     */
    public WishlistTO getWishlist() {
        return wishlist;
    }

    /**
     * Legt den Wert der wishlist-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link WishlistTO }
     *     
     */
    public void setWishlist(WishlistTO value) {
        this.wishlist = value;
    }

}
