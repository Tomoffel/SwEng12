
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ratingResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ratingResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
 *         &lt;element name="ratings" type="{http://integration.shelp.de/}ratingTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ratingResponse", propOrder = {
    "ratings"
})
public class RatingResponse
    extends ReturnCodeResponse
{

    @XmlElement(nillable = true)
    protected List<RatingTO> ratings;

    /**
     * Gets the value of the ratings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ratings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRatings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RatingTO }
     * 
     * 
     */
    public List<RatingTO> getRatings() {
        if (ratings == null) {
            ratings = new ArrayList<RatingTO>();
        }
        return this.ratings;
    }

}
