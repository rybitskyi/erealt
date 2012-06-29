package biz.rageintegro.erealt.domain;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public enum OperationType {
    SALE_FLAT("SaleFlat"),
    SALE_HOUSE("SaleHouse"),
    SALE_LAND("SaleLand"),
    LEASE_FLAT("LeaseFlat"),
    LEASE_HOUSE("LeaseHouse");

    private String key;

    private OperationType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
    public static OperationType getInstanceByKey(String key) {
        if (key == null) {
            return null;
        }
        for (OperationType type : values()) {
            if (key.equals(type.getKey())) {
                return type;
            }
        }
        return null;
    } 
}
