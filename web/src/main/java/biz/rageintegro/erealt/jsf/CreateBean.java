package biz.rageintegro.erealt.jsf;

import biz.rageintegro.erealt.domain.*;
import biz.rageintegro.erealt.jsf.converter.*;
import biz.rageintegro.erealt.reference.RoomCountType;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.*;

/**
 * User: yury.ribitsky
 * Date: 6/12/12
 */
@ManagedBean
@ViewScoped
public class CreateBean {
    private List<District> districtList;
    private District district;
    private List<Region> regionList;
    private Region region;
    private List<Street> streetList;
    private Street street;
    private String streetObjectNo;
    private Integer price;
    private String description;
    private Map<String, RoomCountType> roomTypeList;
    private RoomCountType roomCount;
    private List<OperationType> operationTypeList;
    private OperationType operationType;
    private Integer flatAreaTotal;
    private Integer flatAreaResidental;
    private Integer flatAreaKitchen;
    private Integer flatFloor;
    private Integer flatBuildFloor;
    private Integer plottage;
    private Integer houseArea;
    private HtmlOutputLabel streetObjectNoLabel;
    private ExpressionFactory expressionFactory;
    private ELContext elContext;
    private UploadedFile file;

    @PostConstruct
    public void init() {
        operationType = OperationType.SALE_FLAT;
        operationTypeList = Arrays.asList(OperationType.values());
        districtList = District.findAllDistricts();
        if (!districtList.isEmpty()) {
            district = districtList.get(0);
        }
        populateRegions();
        streetList = Collections.emptyList();

        roomTypeList = new LinkedHashMap<String, RoomCountType>();

        for (RoomCountType roomCountType : RoomCountType.values()) {
            roomTypeList.put(roomCountType.getCaption(), roomCountType);
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        expressionFactory = application.getExpressionFactory();
        elContext = facesContext.getELContext();

        streetObjectNoLabel = new HtmlOutputLabel();
        streetObjectNoLabel.setFor("streetObjectNo");
        streetObjectNoLabel.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{messages.field_label_buildNo}: *", String.class));
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    public void handleDistrictChange() {
        populateRegions();
    }

    public void handleRegionChange() {
        if (region != null && region.getId() != null) {
            streetList = Street.findStreetsByRegion(region.getId());
        } else {
            streetList = Collections.emptyList();
        }
    }

    public Map<String, RoomCountType> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(Map<String, RoomCountType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }

    private void populateRegions() {
        if (district != null && district.getId() != null) {
            regionList = Region.findRegionsByDistrict(district.getId());
        } else {
            regionList = Collections.emptyList();
        }
    }

    public RoomCountType getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(RoomCountType roomCount) {
        this.roomCount = roomCount;
    }

    public List<Street> getStreetList() {
        return streetList;
    }

    public void setStreetList(List<Street> streetList) {
        this.streetList = streetList;
    }

    public void create(ActionEvent actionEvent) {
        AbstractObject entity = createNewEntity();
        entity.persist();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New object added successfully"));
    }

    private AbstractObject createNewEntity() {
        EntityFactory entityFactory = null;
        switch (operationType) {
            case SALE_FLAT:
                entityFactory = SaleFlatFactory.getInstance();
                break;
            case LEASE_FLAT:
                entityFactory = LeaseFlatFactory.getInstance();
                break;
            case SALE_HOUSE:
                entityFactory = SaleHouseFactory.getInstance();
                break;
            case LEASE_HOUSE:
                entityFactory = LeaseHouseFactory.getInstance();
                break;
            case SALE_LAND:
                entityFactory = SaleLandFactory.getInstance();
                break;
        }
        return entityFactory.createEntity(this);
    }

    public List<OperationType> getOperationTypeList() {
        return operationTypeList;
    }

    public void setOperationTypeList(List<OperationType> operationTypeList) {
        this.operationTypeList = operationTypeList;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getStreetObjectNo() {
        return streetObjectNo;
    }

    public void setStreetObjectNo(String streetObjectNo) {
        this.streetObjectNo = streetObjectNo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFlatSelected() {
        return operationType == OperationType.SALE_FLAT || operationType == OperationType.LEASE_FLAT;
    }

    public boolean isLandSelected() {
        return operationType == OperationType.SALE_LAND || isHouseSelected();
    }

    public boolean isHouseSelected() {
        return operationType == OperationType.SALE_HOUSE ||
                operationType == OperationType.LEASE_HOUSE;
    }

    public void handleOperationTypeChange() {
        streetObjectNoLabel.setValueExpression("value", expressionFactory.createValueExpression(elContext,
                "#{messages.field_label_buildNo}:" + (isFlatSelected() ? " *" : ""), String.class));
    }

    public String getFlatArea() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFlatAreaTotal() != null ? getFlatAreaTotal() : "0").append("/");
        sb.append(getFlatAreaResidental() != null ? getFlatAreaResidental() : "0").append("/");
        sb.append(getFlatAreaKitchen() != null ? getFlatAreaKitchen() : "0");
        return sb.toString();
    }

    public Integer getFlatAreaTotal() {
        return flatAreaTotal;
    }

    public void setFlatAreaTotal(Integer flatAreaTotal) {
        this.flatAreaTotal = flatAreaTotal;
    }

    public Integer getFlatAreaResidental() {
        return flatAreaResidental;
    }

    public void setFlatAreaResidental(Integer flatAreaResidental) {
        this.flatAreaResidental = flatAreaResidental;
    }

    public Integer getFlatAreaKitchen() {
        return flatAreaKitchen;
    }

    public void setFlatAreaKitchen(Integer flatAreaKitchen) {
        this.flatAreaKitchen = flatAreaKitchen;
    }

    public Integer getFlatFloor() {
        return flatFloor;
    }

    public void setFlatFloor(Integer flatFloor) {
        this.flatFloor = flatFloor;
    }

    public Integer getFlatBuildFloor() {
        return flatBuildFloor;
    }

    public void setFlatBuildFloor(Integer flatBuildFloor) {
        this.flatBuildFloor = flatBuildFloor;
    }

    public Integer getPlottage() {
        return plottage;
    }

    public void setPlottage(Integer plottage) {
        this.plottage = plottage;
    }

    public Integer getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Integer houseArea) {
        this.houseArea = houseArea;
    }

    public HtmlOutputLabel getStreetObjectNoLabel() {
        return streetObjectNoLabel;
    }

    public void setStreetObjectNoLabel(HtmlOutputLabel streetObjectNoLabel) {
        this.streetObjectNoLabel = streetObjectNoLabel;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        setFile(event.getFile());
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
