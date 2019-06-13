package com.example.myapplicationtest.Enums;

public enum EnumAsync {
    LastID("LastID"),LastIdSong("LastIdSong"),GenreId("GenreId"), InsertSinger("InsertSinger"),
    LastIDPoet("LastIDPoet"),LastIDComp("LastIDComp"), //ASYNC REGISTRATION
    Genre("Genre"), Goal("Goal"),Sol("Sol"),Topic("Topic"),Poet("Poet"),Instrument("Instrument"),Composer("Composer"),RelevantPoets("RelevantPoets"),
    RelevantComposers("RelevantComposers"),DuplicateId("DuplicateId"), YouTube("YouTube")

    ; //ASYNCHELPER

    private String enumAsync;

    EnumAsync(String enumAsync) {
        this.enumAsync=enumAsync;
    }

    public String getEnumAsync(){
        return enumAsync;
    }
}
