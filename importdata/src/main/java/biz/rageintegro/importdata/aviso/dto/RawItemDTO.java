package biz.rageintegro.importdata.aviso.dto;

import biz.rageintegro.erealt.domain.stub.Phone;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "rawItem")
@XmlType(name = "rawItemType")
public class RawItemDTO implements Serializable{
    @XmlElement(required = true)
    private String title;

    @XmlElement(required = true)
    private String regionName;

    @XmlElement(required = true)
    private String address;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private String price;

    @XmlElement(required = true)
    private Date date;

    @XmlElement(required = true)
    private long id;

    @XmlElement(required = true)
    private long eid;

    private File photoFile;

    private String photoPath;

    @XmlElementWrapper(name = "phones", required = true)
    @XmlElement(name = "phone", required = true)
    private List<Phone> phones;

    @XmlElement(required = true)
    private String normalizedPhones;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    @XmlTransient
    public String getPhotoExt() {
        if (photoPath == null || photoPath.length() == 0) {
            return null;
        }
        int dot = photoPath.lastIndexOf(".");
        if (dot > -1) {
            return photoPath.substring(dot + 1);
        }
        else {
            return null;
        }
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Deprecated
    public String getAddress() {
        return address;
    }

    @Deprecated
    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getNormalizedPhones() {
        return normalizedPhones;
    }

    public void setNormalizedPhones(String normalizedPhones) {
        this.normalizedPhones = normalizedPhones;
    }

    public File getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id).append(", ");
        sb.append("eid=").append(eid).append(", ");
        sb.append("title=").append(title).append(", ");
        sb.append("regionName=").append(regionName).append(", ");
        sb.append("address=").append(address).append(", ");
        sb.append("photoFile=").append(photoFile != null ? photoFile.getAbsolutePath() : "null").append(", ");
        sb.append("photoPath=").append(photoPath).append(", ");
        sb.append("description=").append(description).append(", ");
        sb.append("price=").append(price).append(", ");
        sb.append("date=").append(date).append(", ");
        if (phones != null) {
            for (Phone phone : phones) {
                sb.append("phone=").append("(").append(phone.getCode()).append(")").append(phone.getPhoneNumber()).append(", ");
            }
        }
        sb.append("normalizedPhones=").append(normalizedPhones).append(", ");
        return sb.toString();
    }
}
