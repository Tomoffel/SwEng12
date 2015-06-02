
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für capacitiesResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="capacitiesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
 *         &lt;element name="capacities" type="{http://integration.shelp.de/}capacityTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "capacitiesResponse", propOrder = {
    "capacities"
})
public class CapacitiesResponse
    extends ReturnCodeResponse
{

    @XmlElement(nillable = true)
    protected List<CapacityTO> capacities;

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
     * {@link CapacityTO }
     * 
     * 
     */
    public List<CapacityTO> getCapacities() {
        if (capacities == null) {
            capacities = new ArrayList<CapacityTO>();
        }
        return this.capacities;
    }

}
