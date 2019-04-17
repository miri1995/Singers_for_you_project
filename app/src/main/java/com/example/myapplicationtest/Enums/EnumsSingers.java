package com.example.myapplicationtest.Enums;

public enum EnumsSingers {
    Weak("Weak"),Normal("Normal"),Strong("Strong"),
    Slow("Slow"),Medium("Medium"),Fast("Fast"),
    High("High"),Low("Low"),select("select"),
    singer("singer"),poets("poets"),composer("composer");

    private String enums;

    EnumsSingers(String enums) {
        this.enums=enums;
    }

    public String getEnums(){
        return enums;
    }
}
