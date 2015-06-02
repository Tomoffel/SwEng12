
package de.shelp.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ratingTO complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ratingTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="notice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sourceUser" type="{http://integration.shelp.de/}userTO" minOccurs="0"/>
 *         &lt;element name="targetUser" type="{http://integration.shelp.de/}userTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ratingTO", propOrder = {
    "id",
    "notice",
    "rating",
    "sourceUser",
    "targetUser"
})
public class RatingTO {

    protected long id;
    protected String notice;
    protected int rating;
    protected UserTO sourceUser;
    protected UserTO targetUser;

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
     * Ruft den Wert der rating-Eigenschaft ab.
     * 
     */
    public int getRating() {
        return rating;
    }

    /**
     * Legt den Wert der rating-Eigenschaft fest.
     * 
     */
    public void setRating(int value) {
        this.rating = value;
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

}
