
package biz.rageintegro.erealt.domain.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for land complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="land">
 *   &lt;complexContent>
 *     &lt;extension base="{http://erealt.com.ua/domain}abstractObject">
 *       &lt;sequence>
 *         &lt;element name="plottage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "land", propOrder = {
    "plottage"
})
@XmlSeeAlso({
    SaleLand.class,
    House.class
})
public abstract class Land
    extends AbstractObject
{

    protected Integer plottage;

    /**
     * Gets the value of the plottage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPlottage() {
        return plottage;
    }

    /**
     * Sets the value of the plottage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPlottage(Integer value) {
        this.plottage = value;
    }

}
