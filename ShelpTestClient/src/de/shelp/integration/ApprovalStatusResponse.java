
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für approvalStatusResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="approvalStatusResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
 *         &lt;element name="states" type="{http://integration.shelp.de/}approvalStatusTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "approvalStatusResponse", propOrder = {
    "states"
})
public class ApprovalStatusResponse
    extends ReturnCodeResponse
{

    @XmlElement(nillable = true)
    protected List<ApprovalStatusTO> states;

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
     * {@link ApprovalStatusTO }
     * 
     * 
     */
    public List<ApprovalStatusTO> getStates() {
        if (states == null) {
            states = new ArrayList<ApprovalStatusTO>();
        }
        return this.states;
    }

}
