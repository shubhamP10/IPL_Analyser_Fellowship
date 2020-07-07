package com.bridgelabz.iplanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String match;

    @CsvBindByName(column = "Inns", required = true)
    public String innings;

    @CsvBindByName(column = "NO", required = true)
    public String notOut;

    @CsvBindByName(column = "Runs", required = true)
    public String runs;

    @CsvBindByName(column = "HS", required = true)
    public String highestScore;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName(column = "100", required = true)
    public String hundreds;

    @CsvBindByName(column = "50", required = true)
    public String fifties;

    @CsvBindByName(column = "4s", required = true)
    public String fours;

    @CsvBindByName(column = "6s", required = true)
    public String sixes;
}
