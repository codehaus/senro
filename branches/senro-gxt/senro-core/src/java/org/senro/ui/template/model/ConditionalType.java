//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-646 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.09.19 at 06:24:00 PM EEST 
//


package org.senro.ui.template.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConditionalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConditionalType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.siveco.ro/senro/DesignerComponent}UIComponentType">
 *       &lt;sequence>
 *         &lt;element name="If" type="{http://www.siveco.ro/senro/DesignerComponent}IfType"/>
 *         &lt;element name="Else" type="{http://www.siveco.ro/senro/DesignerComponent}ElseType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConditionalType", propOrder = {
    "_if",
    "_else"
})
public class ConditionalType
    extends UIComponentType
{

    @XmlElement(name = "If", required = true)
    protected IfType _if;
    @XmlElement(name = "Else", required = true)
    protected ElseType _else;

    /**
     * Gets the value of the if property.
     * 
     * @return
     *     possible object is
     *     {@link IfType }
     *     
     */
    public IfType getIf() {
        return _if;
    }

    /**
     * Sets the value of the if property.
     * 
     * @param value
     *     allowed object is
     *     {@link IfType }
     *     
     */
    public void setIf(IfType value) {
        this._if = value;
    }

    /**
     * Gets the value of the else property.
     * 
     * @return
     *     possible object is
     *     {@link ElseType }
     *     
     */
    public ElseType getElse() {
        return _else;
    }

    /**
     * Sets the value of the else property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElseType }
     *     
     */
    public void setElse(ElseType value) {
        this._else = value;
    }

}