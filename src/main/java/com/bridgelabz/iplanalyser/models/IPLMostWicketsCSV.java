package com.bridgelabz.iplanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class IPLMostWicketsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String match;

    @CsvBindByName(column = "Inns", required = true)
    public String innings;

    @CsvBindByName(column = "Ov", required = true)
    public String overs;

    @CsvBindByName(column = "Runs", required = true)
    public String runs;

    @CsvBindByName(column = "Wkts", required = true)
    public String wickets;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "Econ", required = true)
    public String economy;

    @CsvBindByName(column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public String fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public String fiveWickets;

}
