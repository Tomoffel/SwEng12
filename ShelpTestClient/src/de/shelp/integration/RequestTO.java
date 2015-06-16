
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="updated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wishes" type="{http://integration.shelp.de/}wishlistItemTO" maxOccurs="unbounded" minOccurs="0"/>
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
    "updated",
    "wishes"
})
public class RequestTO {

    protected long id;
    protected String notice;
    protected UserTO sourceUser;
    protected RequestStatus status;
    protected UserTO targetUser;
    protected TourTO tour;
    protected boolean updated;
    @XmlElement(nillable = true)
    protected List<WishlistItemTO> wishes;

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
     * Ruft den Wert der updated-Eigenschaft ab.
     * 
     */
    public boolean isUpdated() {
        return updated;
    }

    /**
     * Legt den Wert der updated-Eigenschaft fest.
     * 
     */
    public void setUpdated(boolean value) {
        this.updated = value;
    }

    /**
     * Gets the value of the wishes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wishes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWishes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WishlistItemTO }
     * 
     * 
     */
    public List<WishlistItemTO> getWishes() {
        if (wishes == null) {
            wishes = new ArrayList<WishlistItemTO>();
        }
        return this.wishes;
    }

}
