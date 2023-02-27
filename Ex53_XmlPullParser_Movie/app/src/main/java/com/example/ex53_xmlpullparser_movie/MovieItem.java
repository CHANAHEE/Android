package com.example.ex53_xmlpullparser_movie;

public class MovieItem {
    String rank, movieNm, openDt, audiAcc;

    // 3_ 안받는거 하나 받는거 하나 두개를 만드는 습관을 가져보자. 왜그런지는 모르겠네.

    public MovieItem() {
    }

    public MovieItem(String rank, String movieNm, String openDt, String audiAcc) {
        this.rank = rank;
        this.movieNm = movieNm;
        this.openDt = openDt;
        this.audiAcc = audiAcc;
    }
}
