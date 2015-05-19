
package de.shelp.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für tourResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="tourResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
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
@XmlType(name = "tourResponse", propOrder = {
    "tour"
})
public class TourResponse
    extends ReturnCodeResponse
{

    protected TourTO tour;

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
