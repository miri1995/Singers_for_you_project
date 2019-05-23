package com.example.myapplicationtest.Enums;

public enum EnumTables {

  //TO main
  genreSinger("genreSinger"),topic("topic"),goal("goal"),genrePoet("genrePoet"),genreComposer("genreComposer"),


   //artists
    artist_id("artist_id"),artist_name("artist_name"),artist_hotness("artist_hotness"),

    //songs
    song_id("song_id"),song_name("song_name"),song_tempo("song_tempo"),song_loudness("song_loudness"),song_artist_id("song_artist_id"),

    //genreartists
    /*artist_id*/ genre_id("genre_id"),

    //genreSinger
    /*genre_id*/ /*genreSinger("genreSinger")*/

    //poets
    poet_id("poet_id"),poet_name("poet_name"),song_topic("song_topic"),/*goal("goal")*/

    //composers
    composer_id("composer_id"),	composer_name("composer_name"),	composers_genre("composers_genre"),
    musical_instrument("musical_instrument"),	composers_location("composers_location"),
    composers_loudness("composers_loudness"),	composers_tempo("composers_tempo")
    ;

    private String enums;

    EnumTables(String enums) {
        this.enums=enums;
    }

    public String getEnums(){
        return enums;
    }



}
