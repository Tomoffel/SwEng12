
package de.shelp.integration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für capacity.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="capacity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LARGE_TRUNK"/>
 *     &lt;enumeration value="MIDDLE_TRUNK"/>
 *     &lt;enumeration value="SMALL_TRUNK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "capacity")
@XmlEnum
public enum Capacity {

    LARGE_TRUNK,
    MIDDLE_TRUNK,
    SMALL_TRUNK;

    public String value() {
        return name();
    }

    public static Capacity fromValue(String v) {
        return valueOf(v);
    }

}
