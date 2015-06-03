
package de.shelp.integration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für friendshipStatus.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="friendshipStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACCEPT"/>
 *     &lt;enumeration value="DENIED"/>
 *     &lt;enumeration value="ASKED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "friendshipStatus")
@XmlEnum
public enum FriendshipStatus {

    ACCEPT,
    DENIED,
    ASKED;

    public String value() {
        return name();
    }

    public static FriendshipStatus fromValue(String v) {
        return valueOf(v);
    }

}
