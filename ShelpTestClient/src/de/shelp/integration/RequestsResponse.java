
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für requestsResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="requestsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
 *         &lt;element name="requests" type="{http://integration.shelp.de/}requestTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tour" type="{http://integration.shelp.de/}tourTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestsResponse", propOrder = {
    "requests",
    "tour"
})
public class RequestsResponse
    extends ReturnCodeResponse
{

    @XmlElement(nillable = true)
    protected List<RequestTO> requests;
    protected TourTO tour;

    /**
     * Gets the value of the requests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequestTO }
     * 
     * 
     */
    public List<RequestTO> getRequests() {
        if (requests == null) {
            requests = new ArrayList<RequestTO>();
        }
        return this.requests;
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

}
