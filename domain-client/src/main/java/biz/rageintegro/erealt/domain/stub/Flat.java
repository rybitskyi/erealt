
package biz.rageintegro.erealt.domain.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for flat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flat">
 *   &lt;complexContent>
 *     &lt;extension base="{http://erealt.com.ua/domain}abstractObject">
 *       &lt;sequence>
 *         &lt;element name="roomCount" type="{http://ws.domain.erealt.rageintegro.biz/}roomCountType" minOccurs="0"/>
 *         &lt;element name="flatArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flatFloor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="buildFloor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flat", propOrder = {
    "roomCount",
    "flatArea",
    "flatFloor",
    "buildFloor"
})
@XmlSeeAlso({
    SaleFlat.class,
    LeaseFlat.class
})
public abstract class Flat
    extends AbstractObject
{

    protected RoomCountType roomCount;
    protected String flatArea;
    protected Integer flatFloor;
    protected Integer buildFloor;

    /**
     * Gets the value of the roomCount property.
     * 
     * @return
     *     possible object is
     *     {@link RoomCountType }
     *     
     */
    public RoomCountType getRoomCount() {
        return roomCount;
    }

    /**
     * Sets the value of the roomCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomCountType }
     *     
     */
    public void setRoomCount(RoomCountType value) {
        this.roomCount = value;
    }

    /**
     * Gets the value of the flatArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlatArea() {
        return flatArea;
    }

    /**
     * Sets the value of the flatArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlatArea(String value) {
        this.flatArea = value;
    }

    /**
     * Gets the value of the flatFloor property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlatFloor() {
        return flatFloor;
    }

    /**
     * Sets the value of the flatFloor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlatFloor(Integer value) {
        this.flatFloor = value;
    }

    /**
     * Gets the value of the buildFloor property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuildFloor() {
        return buildFloor;
    }

    /**
     * Sets the value of the buildFloor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuildFloor(Integer value) {
        this.buildFloor = value;
    }

}
