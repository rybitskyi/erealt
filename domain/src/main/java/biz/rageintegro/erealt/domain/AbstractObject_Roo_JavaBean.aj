package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.AccessUser;
import biz.rageintegro.erealt.domain.District;
import biz.rageintegro.erealt.domain.Region;
import biz.rageintegro.erealt.domain.Street;

import java.io.IOException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.String;
import java.util.*;
import java.lang.StringBuffer;

privileged aspect AbstractObject_Roo_JavaBean {

    public Date AbstractObject.getCreationDate() {
        return this.creationDate;
    }

    public void AbstractObject.setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public District AbstractObject.getDistrict() {
        return this.district;
    }

    public void AbstractObject.setDistrict(District district) {
        this.district = district;
    }

    public Region AbstractObject.getRegion() {
        return this.region;
    }

    public void AbstractObject.setRegion(Region region) {
        this.region = region;
    }

    public Street AbstractObject.getStreet() {
        return this.street;
    }

    public void AbstractObject.setStreet(Street street) {
        this.street = street;
    }

    public String AbstractObject.getStreetObjectNo() {
        return this.streetObjectNo;
    }

    public void AbstractObject.setStreetObjectNo(String streetObjectNo) {
        this.streetObjectNo = streetObjectNo;
    }

    public Integer AbstractObject.getPrice() {
        return this.price;
    }

    public void AbstractObject.setPrice(Integer price) {
        this.price = price;
    }

    public String AbstractObject.getClientName() {
        if (this.clientName != null && this.clientName.length() > 0) {
            return this.clientName;
        } else if (getAccessUser() != null && getAccessUser().getEmail() != null && getAccessUser().getEmail().length() > 0) {
            return getAccessUser().getNameFromEmail();
        } else if (getWeb() != null && getWeb().length() > 0) {
            return getWeb();
        } else {
            return null;
        }
    }

    public void AbstractObject.setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Deprecated
    public String AbstractObject.getTelephone() {
        return this.telephone;
    }

    @Deprecated
    public void AbstractObject.setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Deprecated
    public String AbstractObject.getEmail() {
        return this.email;
    }

    @Deprecated
    public void AbstractObject.setEmail(String email) {
        this.email = email;
    }

    public String AbstractObject.getDescription() {
        return this.description;
    }

    public void AbstractObject.setDescription(String description) {
        this.description = description;
    }

    public AccessUser AbstractObject.getAccessUser() {
        return this.accessUser;
    }

    public void AbstractObject.setAccessUser(AccessUser accessUser) {
        this.accessUser = accessUser;
    }

    public String AbstractObject.getPhotoExt() {
        return this.photoExt;
    }

    public void AbstractObject.setPhotoExt(String photoExt) {
        this.photoExt = photoExt;
    }

    public String AbstractObject.getSource() {
        return this.source;
    }

    public void AbstractObject.setSource(String source) {
        this.source = source;
    }

    public String AbstractObject.getWeb() {
        return this.web;
    }

    public void AbstractObject.setWeb(String web) {
        this.web = web;
    }

    public String AbstractObject.getEid() {
        return this.eid;
    }

    public void AbstractObject.setEid(String eid) {
        this.eid = eid;
    }

    public String AbstractObject.getImg() {
        return this.img;
    }

    public void AbstractObject.setImg(String img) {
        this.img = img;
    }

    public String AbstractObject.getFullAddressName(String delim) {
        StringBuffer sb = new StringBuffer();
        if (getStreet() != null) {
            if (sb.length() > 0) {
                sb.append(delim);
            }
            sb.append(getStreet());
        }
        if (getStreetObjectNo() != null && getStreetObjectNo().length() > 0) {
            if (sb.length() > 0) {
                sb.append(delim);
            }
            sb.append(getStreetObjectNo());
        }
        if (getRegion() != null) {
            if (sb.length() > 0) {
                sb.append(delim);
            }
            sb.append(getRegion());
        }
        if (getDistrict() != null) {
            if (sb.length() > 0) {
                sb.append(delim);
            }
            sb.append(getDistrict());
        }
        return sb.toString();
    }

    public String AbstractObject.getFullAddressName() {
        return getFullAddressName(" ");
    }

    public String AbstractObject.getMapAddressName() {
//todo: add street, region and district name
        StringBuffer sb = new StringBuffer();
        if (getDistrict() != null) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(getDistrict());
        }
        if (getRegion() != null) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(getRegion());
        }
        if (getStreet() != null) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(getStreet());
        }
        if (getStreetObjectNo() != null && getStreetObjectNo().length() > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(getStreetObjectNo());
        }
        return sb.toString();
	}

	public String AbstractObject.getExtId() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.id);
		sb.append("-");
		sb.append(TranslitConverter.translit(getFullAddressName("-")).replace('\\', '/'));
		return sb.toString().replace('/','_');
	}

    public String AbstractObject.getPhotoUrl() {
        return photoManager.getPhotoUrl(this);
    }

    public String AbstractObject.getThumbnailPhotoUrl() {
        return photoManager.getThumbnailPhotoUrl(this);
    }

    @Deprecated
    public Map<String, String> AbstractObject.getQuickInfo() {
        return Collections.EMPTY_MAP;
    }

    public List<QuickInfo> AbstractObject.getQuickInfoList() {
        return Collections.EMPTY_LIST;
    }

    public FileDataWrapper AbstractObject.getFileDataWrapper() {
        return fileDataWrapper;
    }

    public void AbstractObject.setFileDataWrapper(FileDataWrapper fileDataWrapper) {
        this.fileDataWrapper = fileDataWrapper;
    }

    public String AbstractObject.getPhoto() {
        return photo;
    }

    public void AbstractObject.setPhoto(String photo) {
        this.photo = photo;
    }
}
