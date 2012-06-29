
package biz.rageintegro.erealt.domain.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for house complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="house">
 *   &lt;complexContent>
 *     &lt;extension base="{http://erealt.com.ua/domain}land">
 *       &lt;sequence>
 *         &lt;element name="houseArea" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "house", propOrder = {
    "houseArea"
})
@XmlSeeAlso({
    LeaseHouse.class,
    SaleHouse.class
})
public abstract class House
    extends Land
{

    protected Integer houseArea;

    /**
     * Gets the value of the houseArea property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHouseArea() {
        return houseArea;
    }

    /**
     * Sets the value of the houseArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHouseArea(Integer value) {
        this.houseArea = value;
    }

}
