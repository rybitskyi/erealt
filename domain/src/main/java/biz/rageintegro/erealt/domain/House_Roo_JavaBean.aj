package biz.rageintegro.erealt.domain;

privileged aspect House_Roo_JavaBean {
    
    public Integer House.getHouseArea() {
        return this.houseArea;
    }

    public void House.setHouseArea(Integer houseArea) {
        this.houseArea = houseArea;
    }
}
