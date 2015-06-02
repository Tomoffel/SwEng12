
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für tourTO complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="tourTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="approvalStatus" type="{http://integration.shelp.de/}approvalStatusTO" minOccurs="0"/>
 *         &lt;element name="capacity" type="{http://integration.shelp.de/}capacityTO" minOccurs="0"/>
 *         &lt;element name="deliveryConditions" type="{http://integration.shelp.de/}deliveryConditionTO" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="location" type="{http://integration.shelp.de/}locationTO" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://integration.shelp.de/}userTO" minOccurs="0"/>
 *         &lt;element name="paymentConditions" type="{http://integration.shelp.de/}paymentConditionTO" minOccurs="0"/>
 *         &lt;element name="request" type="{http://integration.shelp.de/}requestTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://integration.shelp.de/}tourStatus" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updatedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tourTO", propOrder = {
    "approvalStatus",
    "capacity",
    "deliveryConditions",
    "id",
    "location",
    "owner",
    "paymentConditions",
    "request",
    "status",
    "time",
    "updatedOn"
})
public class TourTO {

    protected ApprovalStatusTO approvalStatus;
    protected CapacityTO capacity;
    protected DeliveryConditionTO deliveryConditions;
    protected long id;
    protected LocationTO location;
    protected UserTO owner;
    protected PaymentConditionTO paymentConditions;
    @XmlElement(nillable = true)
    protected List<RequestTO> request;
    protected TourStatus status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedOn;

    /**
     * Ruft den Wert der approvalStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApprovalStatusTO }
     *     
     */
    public ApprovalStatusTO getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * Legt den Wert der approvalStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovalStatusTO }
     *     
     */
    public void setApprovalStatus(ApprovalStatusTO value) {
        this.approvalStatus = value;
    }

    /**
     * Ruft den Wert der capacity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CapacityTO }
     *     
     */
    public CapacityTO getCapacity() {
        return capacity;
    }

    /**
     * Legt den Wert der capacity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CapacityTO }
     *     
     */
    public void setCapacity(CapacityTO value) {
        this.capacity = value;
    }

    /**
     * Ruft den Wert der deliveryConditions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryConditionTO }
     *     
     */
    public DeliveryConditionTO getDeliveryConditions() {
        return deliveryConditions;
    }

    /**
     * Legt den Wert der deliveryConditions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryConditionTO }
     *     
     */
    public void setDeliveryConditions(DeliveryConditionTO value) {
        this.deliveryConditions = value;
    }

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
     * Ruft den Wert der location-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LocationTO }
     *     
     */
    public LocationTO getLocation() {
        return location;
    }

    /**
     * Legt den Wert der location-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationTO }
     *     
     */
    public void setLocation(LocationTO value) {
        this.location = value;
    }

    /**
     * Ruft den Wert der owner-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link UserTO }
     *     
     */
    public UserTO getOwner() {
        return owner;
    }

    /**
     * Legt den Wert der owner-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link UserTO }
     *     
     */
    public void setOwner(UserTO value) {
        this.owner = value;
    }

    /**
     * Ruft den Wert der paymentConditions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PaymentConditionTO }
     *     
     */
    public PaymentConditionTO getPaymentConditions() {
        return paymentConditions;
    }

    /**
     * Legt den Wert der paymentConditions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentConditionTO }
     *     
     */
    public void setPaymentConditions(PaymentConditionTO value) {
        this.paymentConditions = value;
    }

    /**
     * Gets the value of the request property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the request property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequestTO }
     * 
     * 
     */
    public List<RequestTO> getRequest() {
        if (request == null) {
            request = new ArrayList<RequestTO>();
        }
        return this.request;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TourStatus }
     *     
     */
    public TourStatus getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TourStatus }
     *     
     */
    public void setStatus(TourStatus value) {
        this.status = value;
    }

    /**
     * Ruft den Wert der time-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Legt den Wert der time-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
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

}
