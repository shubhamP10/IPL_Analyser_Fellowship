package com.bridgelabz.iplanalyser;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.services.IPLAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKETS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100,count);
        } catch (IPLAnalyserException e){
            System.out.println("Fail");
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BOWLER);
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER,MOST_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(98,count);
        } catch (IPLAnalyserException e){
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
}