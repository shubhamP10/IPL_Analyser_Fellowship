package com.bridgelabz.iplanalyser.services;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.models.IPLMostRunsCSV;
import com.bridgelabz.iplanalyser.utility.IPLAdapterFactory;
import com.bridgelabz.iplanalyser.utility.IPLUtility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import static java.util.Comparator.comparing;

public class IPLAnalyser {

    public static PlayerType playerType;
    Map<String, IPLAnalyserDAO> iplAnalyserMap = null;

    public IPLAnalyser(PlayerType playerType) {
        IPLAnalyser.playerType = playerType;
    }

    public int loadIPLData(PlayerType playerType, String csvFilePath) throws IPLAnalyserException {
        iplAnalyserMap = IPLAdapterFactory.getIPLDataObject(playerType, csvFilePath);
        return iplAnalyserMap.size();
    }

    public IPLMostRunsCSV getTopBattingAveragePlayer() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = comparing(iplData -> iplData.average);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostRunsCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostRunsCSV[0];
    }

    public IPLMostRunsCSV getMaximumFourHitter() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = comparing(iplData -> iplData.fours);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }

    public IPLMostRunsCSV getMaximumSixHitter() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = comparing(iplData -> iplData.sixes);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }

    public IPLMostRunsCSV getTopStrikeRatePlayer() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = comparing(iplData -> iplData.strikeRate);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }

    public IPLMostRunsCSV getPlayerWithBestStrikeRateWith4sAnd6s(IPLMostRunsCSV maximumFourHitter, IPLMostRunsCSV maximumSixHitter) {
        IPLMostRunsCSV bestPlayer = maximumSixHitter;
        if(maximumFourHitter.average > maximumSixHitter.average){
               bestPlayer = maximumFourHitter;
        }
        return bestPlayer;
    }

    public enum PlayerType {BATSMAN, BOWLER}
}
