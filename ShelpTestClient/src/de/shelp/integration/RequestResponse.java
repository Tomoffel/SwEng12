
package de.shelp.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für requestResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="requestResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
 *         &lt;element name="requestTO" type="{http://integration.shelp.de/}requestTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestResponse", propOrder = {
    "requestTO"
})
public class RequestResponse
    extends ReturnCodeResponse
{

    protected RequestTO requestTO;

    /**
     * Ruft den Wert der requestTO-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RequestTO }
     *     
     */
    public RequestTO getRequestTO() {
        return requestTO;
    }

    /**
     * Legt den Wert der requestTO-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestTO }
     *     
     */
    public void setRequestTO(RequestTO value) {
        this.requestTO = value;
    }

}
