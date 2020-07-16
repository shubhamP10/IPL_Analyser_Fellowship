package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.services.IPLAnalyser;
import com.bridgelabz.iplanalyser.utility.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, count);
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BOWLER);
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(99, count);
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }

    //    UC1
    @Test
    public void getBestAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.AVERAGE);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("MS Dhoni", iplCSV[0].playerName);
    }

    //    UC2
    @Test
    public void giveIPLMostRunsCSVFile_ShouldReturn_PlayerWith_TopStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKE_RATE);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Ishant Sharma", iplCSV[0].playerName);
    }

    // UC3
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_PlayersNameWhoHitsMaximum_SixesAndFours() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.SIXES_AND_FOURS);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Andre Russell", iplCSV[0].playerName);
    }

    // UC4
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_BestStrikingRatesWith4sAnd6s() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.FOURS_AND_SIXES_WITH_STRIKERATE);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Andre Russell", iplCSV[0].playerName);
    }

    // UC5
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_GreatAverageWithBestStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.AVERAGE_WITH_STRIKERATE);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("MS Dhoni", iplCSV[0].playerName);
    }

    //    UC6
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHits_MaximumRunsWithBestAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.MAXIMUM_RUNS_WITH_BEST_AVERAGE);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("David Warner", iplCSV[0].playerName);
    }

    //    UC7
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnCricketerWhoHas_BestBowlingAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.BEST_BOWLING_AVERAGE);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }

    //    UC8
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestStrikingRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKE_RATE);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }

    //    UC9
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestEconomy() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.ECONOMY);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }
}