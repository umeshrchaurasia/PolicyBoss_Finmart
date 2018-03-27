package com.datacomp.magicfinmart.motor.privatecar.adapter;

/**
 * Created by Rajeev Ranjan on 19/01/2018.
 */

public class PremiumBreakUpAddonEntity {
    String name;
    String value;
    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public PremiumBreakUpAddonEntity(String name, String value) {
        this.name = name;
        this.value = value;
        this.isSelected = true;
    }

    public PremiumBreakUpAddonEntity(String name, String value, boolean isSelected) {
        this.name = name;
        this.value = value;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}