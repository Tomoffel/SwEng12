
package de.shelp.integration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für tourStatus.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="tourStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PLANNED"/>
 *     &lt;enumeration value="CANCELLED"/>
 *     &lt;enumeration value="CLOSED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tourStatus")
@XmlEnum
public enum TourStatus {

    PLANNED,
    CANCELLED,
    CLOSED;

    public String value() {
        return name();
    }

    public static TourStatus fromValue(String v) {
        return valueOf(v);
    }

}
