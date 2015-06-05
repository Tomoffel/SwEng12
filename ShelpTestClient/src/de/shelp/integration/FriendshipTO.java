
package de.shelp.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für friendshipTO complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="friendshipTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="initiatorUser" type="{http://integration.shelp.de/}userTO" minOccurs="0"/>
 *         &lt;element name="recipientUser" type="{http://integration.shelp.de/}userTO" minOccurs="0"/>
 *         &lt;element name="status" type="{http://integration.shelp.de/}friendshipStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "friendshipTO", propOrder = {
    "changedOn",
    "id",
    "initiatorUser",
    "recipientUser",
    "status"
})
public class FriendshipTO {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar changedOn;
    protected int id;
    protected UserTO initiatorUser;
    protected UserTO recipientUser;
    protected FriendshipStatus status;

    /**
     * Ruft den Wert der changedOn-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChangedOn() {
        return changedOn;
    }

    /**
     * Legt den Wert der changedOn-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChangedOn(XMLGregorianCalendar value) {
        this.changedOn = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der initiatorUser-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link UserTO }
     *     
     */
    public UserTO getInitiatorUser() {
        return initiatorUser;
    }

    /**
     * Legt den Wert der initiatorUser-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link UserTO }
     *     
     */
    public void setInitiatorUser(UserTO value) {
        this.initiatorUser = value;
    }

    /**
     * Ruft den Wert der recipientUser-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link UserTO }
     *     
     */
    public UserTO getRecipientUser() {
        return recipientUser;
    }

    /**
     * Legt den Wert der recipientUser-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link UserTO }
     *     
     */
    public void setRecipientUser(UserTO value) {
        this.recipientUser = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link FriendshipStatus }
     *     
     */
    public FriendshipStatus getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link FriendshipStatus }
     *     
     */
    public void setStatus(FriendshipStatus value) {
        this.status = value;
    }

}
