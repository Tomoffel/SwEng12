
package de.shelp.integration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für searchTour complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="searchTour">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="arg2" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="arg3" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="arg4" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="arg5" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchTour", propOrder = {
    "arg0",
    "arg1",
    "arg2",
    "arg3",
    "arg4",
    "arg5"
})
public class SearchTour {

    protected int arg0;
    protected long arg1;
    protected long arg2;
    protected long arg3;
    protected boolean arg4;
    protected int arg5;

    /**
     * Ruft den Wert der arg0-Eigenschaft ab.
     * 
     */
    public int getArg0() {
        return arg0;
    }

    /**
     * Legt den Wert der arg0-Eigenschaft fest.
     * 
     */
    public void setArg0(int value) {
        this.arg0 = value;
    }

    /**
     * Ruft den Wert der arg1-Eigenschaft ab.
     * 
     */
    public long getArg1() {
        return arg1;
    }

    /**
     * Legt den Wert der arg1-Eigenschaft fest.
     * 
     */
    public void setArg1(long value) {
        this.arg1 = value;
    }

    /**
     * Ruft den Wert der arg2-Eigenschaft ab.
     * 
     */
    public long getArg2() {
        return arg2;
    }

    /**
     * Legt den Wert der arg2-Eigenschaft fest.
     * 
     */
    public void setArg2(long value) {
        this.arg2 = value;
    }

    /**
     * Ruft den Wert der arg3-Eigenschaft ab.
     * 
     */
    public long getArg3() {
        return arg3;
    }

    /**
     * Legt den Wert der arg3-Eigenschaft fest.
     * 
     */
    public void setArg3(long value) {
        this.arg3 = value;
    }

    /**
     * Ruft den Wert der arg4-Eigenschaft ab.
     * 
     */
    public boolean isArg4() {
        return arg4;
    }

    /**
     * Legt den Wert der arg4-Eigenschaft fest.
     * 
     */
    public void setArg4(boolean value) {
        this.arg4 = value;
    }

    /**
     * Ruft den Wert der arg5-Eigenschaft ab.
     * 
     */
    public int getArg5() {
        return arg5;
    }

    /**
     * Legt den Wert der arg5-Eigenschaft fest.
     * 
     */
    public void setArg5(int value) {
        this.arg5 = value;
    }

}
