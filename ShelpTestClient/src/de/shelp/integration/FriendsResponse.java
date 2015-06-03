
package de.shelp.integration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für friendsResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="friendsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://integration.shelp.de/}returnCodeResponse">
 *       &lt;sequence>
 *         &lt;element name="friends" type="{http://integration.shelp.de/}friendshipTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "friendsResponse", propOrder = {
    "friends"
})
public class FriendsResponse
    extends ReturnCodeResponse
{

    @XmlElement(nillable = true)
    protected List<FriendshipTO> friends;

    /**
     * Gets the value of the friends property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the friends property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFriends().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FriendshipTO }
     * 
     * 
     */
    public List<FriendshipTO> getFriends() {
        if (friends == null) {
            friends = new ArrayList<FriendshipTO>();
        }
        return this.friends;
    }

}
