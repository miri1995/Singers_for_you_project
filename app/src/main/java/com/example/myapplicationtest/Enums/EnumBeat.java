package com.example.myapplicationtest.Enums;

public enum EnumBeat {
    Slow("Slow"),Medium("Medium"),Fast("Fast");

    private String beat;

    EnumBeat(String beat) {
        this.beat=beat;
    }

    public String getBeat(){
        return beat;
    }
}
