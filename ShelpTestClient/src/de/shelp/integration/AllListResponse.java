
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für allListResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="allListResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
 *         &lt;element name="capacities" type="{http://integration.shelp.de/}capacity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deliveryConditions" type="{http://integration.shelp.de/}deliveryCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="locations" type="{http://integration.shelp.de/}locationTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paymentConditions" type="{http://integration.shelp.de/}paymentCondition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="states" type="{http://integration.shelp.de/}approvalStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "allListResponse", propOrder = {
    "capacities",
    "deliveryConditions",
    "locations",
    "paymentConditions",
    "states"
})
public class AllListResponse
    extends ReturnCodeResponse
{

    @XmlElement(nillable = true)
    protected List<Capacity> capacities;
    @XmlElement(nillable = true)
    protected List<DeliveryCondition> deliveryConditions;
    @XmlElement(nillable = true)
    protected List<LocationTO> locations;
    @XmlElement(nillable = true)
    protected List<PaymentCondition> paymentConditions;
    @XmlElement(nillable = true)
    protected List<ApprovalStatus> states;

    /**
     * Gets the value of the capacities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the capacities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCapacities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Capacity }
     * 
     * 
     */
    public List<Capacity> getCapacities() {
        if (capacities == null) {
            capacities = new ArrayList<Capacity>();
        }
        return this.capacities;
    }

    /**
     * Gets the value of the deliveryConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryCondition }
     * 
     * 
     */
    public List<DeliveryCondition> getDeliveryConditions() {
        if (deliveryConditions == null) {
            deliveryConditions = new ArrayList<DeliveryCondition>();
        }
        return this.deliveryConditions;
    }

    /**
     * Gets the value of the locations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocationTO }
     * 
     * 
     */
    public List<LocationTO> getLocations() {
        if (locations == null) {
            locations = new ArrayList<LocationTO>();
        }
        return this.locations;
    }

    /**
     * Gets the value of the paymentConditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentConditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentCondition }
     * 
     * 
     */
    public List<PaymentCondition> getPaymentConditions() {
        if (paymentConditions == null) {
            paymentConditions = new ArrayList<PaymentCondition>();
        }
        return this.paymentConditions;
    }

    /**
     * Gets the value of the states property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the states property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApprovalStatus }
     * 
     * 
     */
    public List<ApprovalStatus> getStates() {
        if (states == null) {
            states = new ArrayList<ApprovalStatus>();
        }
        return this.states;
    }

}
