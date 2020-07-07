package com.bridgelabz.iplanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public int highestScore;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fifties;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public IPLMostRunsCSV() {
    }

    public IPLMostRunsCSV(String player, int match, int innings, int notOut, int runs, int highestScore,
                          double average, double strikeRate, int hundreds, int fifties, int fours, int sixes) {
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.notOut = notOut;
        this.runs = runs;
        this.highestScore = highestScore;
        this.average = average;
        this.strikeRate = strikeRate;
        this.hundreds = hundreds;
        this.fifties = fifties;
        this.fours = fours;
        this.sixes = sixes;
    }
}
