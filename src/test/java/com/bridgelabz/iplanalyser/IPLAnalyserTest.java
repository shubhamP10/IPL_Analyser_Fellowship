package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.models.IPLMostRunsCSV;
import com.bridgelabz.iplanalyser.services.IPLAnalyser;
import com.bridgelabz.iplanalyser.utility.SortField;
import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
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
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_GreatAverageWithBestStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
            IPLMostRunsCSV bestPlayerWithStrikeRate = iplAnalyser.getPlayerWithGreatAverageAndBestStrikeRate();
            Assert.assertThat(bestPlayerWithStrikeRate.player, CoreMatchers.is("MS Dhoni"));
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
}