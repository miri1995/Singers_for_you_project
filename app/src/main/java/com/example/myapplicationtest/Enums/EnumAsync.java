package com.example.myapplicationtest.Enums;

public enum EnumAsync {
    LastID("LastID"),LastIdSong("LastIdSong"),GenreId("GenreId"), InsertSinger("InsertSinger"), //ASYNC REGISTRATION
    Genre("Genre"), Goal("Goal"),Sol("Sol"),Topic("Topic"),Poet("Poet"); //ASYNCHELPER

    private String enumAsync;

    EnumAsync(String enumAsync) {
        this.enumAsync=enumAsync;
    }

    public String getEnumAsync(){
        return enumAsync;
    }
}
