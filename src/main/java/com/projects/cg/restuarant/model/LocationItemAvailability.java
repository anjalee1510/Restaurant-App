package com.projects.cg.restuarant.model;

public class LocationItemAvailability {
    // Fields
    private String pId;
    private String itemName;
    private boolean isAvailable;
    private String reasonUnavailable;

    // Constructors
    public LocationItemAvailability() {
    }

    public LocationItemAvailability(String pId, String itemName, boolean isAvailable, String reasonUnavailable) {
        this.pId = pId;
        this.itemName = itemName;
        this.isAvailable = isAvailable;
        this.reasonUnavailable = reasonUnavailable;
    }

    // Getters and Setters
    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getReasonUnavailable() {
        return reasonUnavailable;
    }

    public void setReasonUnavailable(String reasonUnavailable) {
        this.reasonUnavailable = reasonUnavailable;
    }
}
