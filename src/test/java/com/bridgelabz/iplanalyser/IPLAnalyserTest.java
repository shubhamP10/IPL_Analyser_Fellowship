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
    private static final String WRONG_FILE_PATH = "./src/test/resources/wrongFile.csv";
    private static final String NO_DATA_CSV = "src/test/resources/IPL2019FactsheetMostRuns_NoData.csv";

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
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
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(99, count);
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongCSVPathCSVFile_ShouldThrow_Exception() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, WRONG_FILE_PATH);
            Assert.assertEquals(99, count);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

    @Test
    public void givenCSVFileWithNoData_ShouldThrow_Exception() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, NO_DATA_CSV);
            String player = iplAnalyser.getSortedData(SortField.AVERAGE, true);
            IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
            Assert.assertEquals("MS Dhoni", iplCSV[0].playerName);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(e.type, IPLAnalyserException.ExceptionType.NO_DATA);
        }
    }

    //    UC1
    @Test
    public void getBestAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.AVERAGE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("MS Dhoni", iplCSV[0].playerName);
    }

    //    UC2
    @Test
    public void giveIPLMostRunsCSVFile_ShouldReturn_PlayerWith_TopStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKE_RATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Ishant Sharma", iplCSV[0].playerName);
    }

    // UC3
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_PlayersNameWhoHitsMaximum_SixesAndFours() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.SIXES_AND_FOURS, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Andre Russell", iplCSV[0].playerName);
    }

    // UC4
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_BestStrikingRatesWith4sAnd6s() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.FOURS_AND_SIXES_WITH_STRIKERATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Andre Russell", iplCSV[0].playerName);
    }

    // UC5
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_GreatAverageWithBestStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.AVERAGE_WITH_STRIKERATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("MS Dhoni", iplCSV[0].playerName);
    }

    //    UC6
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHits_MaximumRunsWithBestAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.MAXIMUM_RUNS_WITH_BEST_AVERAGE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("David Warner", iplCSV[0].playerName);
    }

    //    UC7
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnCricketerWhoHas_BestBowlingAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.BEST_BOWLING_AVERAGE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }

    //    UC8
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestStrikingRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKE_RATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }

    //    UC9
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestEconomy() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.ECONOMY, false);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Shivam Dube", iplCSV[0].playerName);
    }

    //    UC10
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestStrikeRateWith5wAnd4w() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKERATE_WITH_4W_AND_5W, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Lasith Malinga", iplCSV[0].playerName);
    }

    //    UC11
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_GreatBowlingAverageWithStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.BEST_BOWLING_AVERAGE_WITH_STRIKERATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }

    //    UC12
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoTook_MaxWicketsWithBestBowlingAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.MAX_WICKETS_WITH_BOWLING_AVERAGE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Imran Tahir", iplCSV[0].playerName);
    }
}