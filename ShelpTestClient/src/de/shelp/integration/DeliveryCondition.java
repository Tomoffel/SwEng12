
package de.shelp.integration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für deliveryCondition.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="deliveryCondition">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PICKUP"/>
 *     &lt;enumeration value="BRING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "deliveryCondition")
@XmlEnum
public enum DeliveryCondition {

    PICKUP,
    BRING;

    public String value() {
        return name();
    }

    public static DeliveryCondition fromValue(String v) {
        return valueOf(v);
    }

}
