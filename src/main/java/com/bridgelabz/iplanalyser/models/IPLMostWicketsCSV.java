package com.bridgelabz.iplanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class IPLMostWicketsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public String overs;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "Econ", required = true)
    public String economy;

    @CsvBindByName(column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    public IPLMostWicketsCSV() {
    }

//    public IPLMostWicketsCSV(String player, int match, int innings, String overs, int runs, int wickets,
//                             String average, double economy, String strikeRate, int fourWickets, int fiveWickets) {
//        this.player = player;
//        this.match = match;
//        this.innings = innings;
//        this.overs = overs;
//        this.runs = runs;
//        this.wickets = wickets;
//        this.average = average;
//        this.economy = economy;
//        this.strikeRate = strikeRate;
//        this.fourWickets = fourWickets;
//        this.fiveWickets = fiveWickets;
//    }
}
