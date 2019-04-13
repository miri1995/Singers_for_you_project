package com.example.myapplicationtest.Enums;

public enum EnumAsync {
    LastID("LastId"),LastIdSong("LastIdSong"),GenreId("GenreId"), InsertSinger("InsertSinger"), //ASYNC REGISTRATION
    Genre("Genre"), Goal("Goal"),Sol("Sol"),Topic("Topic"); //ASYNCHELPER

    private String enumAsync;

    EnumAsync(String enumAsync) {
        this.enumAsync=enumAsync;
    }

    public String getEnumAsync(){
        return enumAsync;
    }
}
