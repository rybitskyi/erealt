
package biz.rageintegro.erealt.domain.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roomCountType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="roomCountType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ONE"/>
 *     &lt;enumeration value="TWO"/>
 *     &lt;enumeration value="THREE"/>
 *     &lt;enumeration value="FOUR"/>
 *     &lt;enumeration value="MORE_THAN_FOUR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "roomCountType", namespace = "http://ws.domain.erealt.rageintegro.biz/")
@XmlEnum
public enum RoomCountType {

    ONE,
    TWO,
    THREE,
    FOUR,
    MORE_THAN_FOUR;

    public String value() {
        return name();
    }

    public static RoomCountType fromValue(String v) {
        return valueOf(v);
    }

}
