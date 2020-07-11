package com.bridgelabz.iplanalyser.services;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.models.IPLMostRunsCSV;
import com.bridgelabz.iplanalyser.utility.IPLAdapterFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalyser {
    private final PlayerType playerType;
    Map<String, IPLAnalyserDAO> iplAnalyserMap = null;

    public IPLAnalyser(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int loadIPLData(PlayerType playerType, String csvFilePath) throws IPLAnalyserException {
        iplAnalyserMap = IPLAdapterFactory.getIPLDataObject(playerType, csvFilePath);
        return iplAnalyserMap.size();
    }

    public IPLMostRunsCSV getTopBattingAveragePlayer() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0){
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA,"No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.average);
        ArrayList iplDTO = iplAnalyserMap.values()
                .stream()
//                .filter(iplAnalyserDAO -> iplAnalyserDAO.average != '-')
                .sorted(iplComparator.reversed())
                .map(censusDAO -> censusDAO.getIPLDTO(playerType))
                .collect(toCollection(ArrayList::new));
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostRunsCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
//        CensusUtility.jsonWriter(sortedData, SORTED_US_POPULATION_JSON);
        return iplMostRunsCSV[0];
    }

    public enum PlayerType {BATSMAN, BOWLER, ALL_ROUNDER}


}
