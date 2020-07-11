package com.bridgelabz.iplanalyser.utility;

import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.services.IPLAnalyser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;

public class IPLUtility {
    public static ArrayList sort(Comparator<IPLAnalyserDAO> iplComparator, Map<String, IPLAnalyserDAO> iplAnalyserMap, boolean reversed) {
        return iplAnalyserMap.values()
                .stream()
                .sorted(iplComparator.reversed())
                .map(censusDAO -> censusDAO.getIPLDTO(IPLAnalyser.playerType))
                .collect(toCollection(ArrayList::new));
    }
}
