
package de.shelp.integration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für requestStatus.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="requestStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ASKED"/>
 *     &lt;enumeration value="ACCECPT"/>
 *     &lt;enumeration value="PARTLY_ACCEPT"/>
 *     &lt;enumeration value="DENIED"/>
 *     &lt;enumeration value="REMOVED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "requestStatus")
@XmlEnum
public enum RequestStatus {

    ASKED,
    ACCECPT,
    PARTLY_ACCEPT,
    DENIED,
    REMOVED;

    public String value() {
        return name();
    }

    public static RequestStatus fromValue(String v) {
        return valueOf(v);
    }

}
