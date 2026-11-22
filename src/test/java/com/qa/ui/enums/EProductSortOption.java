package com.qa.enums;

public enum EProductSortOption {
    PRICE_LOW_TO_HIGH("Price (low to high)"),
    PRICE_HIGH_TO_LOW("Price (high to low)"),
    NAME_ASCENDING("Name (A to Z)"),
    NAME_DESCENDING("Name (Z to A)");

    private final String displayName;

    EProductSortOption(String displayName) 
    {
        this.displayName = displayName;
    }

    public String getDisplayName() 
    {
        return displayName;
    }
}