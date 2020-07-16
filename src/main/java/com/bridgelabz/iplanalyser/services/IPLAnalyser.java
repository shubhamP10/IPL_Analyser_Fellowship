package com.bridgelabz.iplanalyser.services;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.utility.IPLAdapterFactory;
import com.bridgelabz.iplanalyser.utility.SortField;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.IntStream;

public class IPLAnalyser {

    public static PlayerType playerType;

    List<IPLAnalyserDAO> iplList = null;
    Map<String, IPLAnalyserDAO> iplAnalyserMap = null;
    Map<SortField, Comparator<IPLAnalyserDAO>> sortMap = null;

    public IPLAnalyser(PlayerType playerType) {
        IPLAnalyser.playerType = playerType;

    }

    public IPLAnalyser() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVERAGE, Comparator.comparing(ipl -> ipl.average));
        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(ipl -> ipl.strikeRate));
        this.sortMap.put(SortField.SIXES_AND_FOURS, Comparator.comparing(ipl -> ipl.sixes + ipl.fours));
        this.sortMap.put(SortField.MAXIMUM_RUNS, Comparator.comparing(ipl -> ipl.runs));
        this.sortMap.put(SortField.BEST_BOWLING_AVERAGE, Comparator.comparing(ipl -> ipl.average));
        this.sortMap.put(SortField.ECONOMY, Comparator.comparing(ipl -> ipl.economy));
        this.sortMap.put(SortField.MAXIMUM_WICKETS, Comparator.comparing(ipl -> ipl.wickets));

        Comparator<IPLAnalyserDAO> foursAndSixesComparator = Comparator.comparing(ipl -> ipl.sixes + ipl.fours);
        Comparator<IPLAnalyserDAO> averageComparator = Comparator.comparing(ipl -> ipl.average);
        Comparator<IPLAnalyserDAO> maxRunsComparator = Comparator.comparing(ipl -> ipl.runs);
        Comparator<IPLAnalyserDAO> fourAndFiveWicketsComparator = Comparator.comparing(ipl -> ipl.fourWickets + ipl.fiveWickets);
        Comparator<IPLAnalyserDAO> maxWicketsComparator = Comparator.comparing(ipl -> ipl.wickets);

        this.sortMap.put(SortField.MAXIMUM_RUNS_WITH_BEST_AVERAGE, maxRunsComparator.thenComparing(ipl -> ipl.average));
        this.sortMap.put(SortField.FOURS_AND_SIXES_WITH_STRIKERATE, foursAndSixesComparator.thenComparing(ipl -> ipl.strikeRate));
        this.sortMap.put(SortField.AVERAGE_WITH_STRIKERATE, averageComparator.thenComparing(ipl -> ipl.strikeRate));
        this.sortMap.put(SortField.STRIKERATE_WITH_4W_AND_5W, fourAndFiveWicketsComparator.thenComparing(ipl -> ipl.strikeRate));
        this.sortMap.put(SortField.BEST_BOWLING_AVERAGE_WITH_STRIKERATE, averageComparator.thenComparing(ipl -> ipl.strikeRate));
        this.sortMap.put(SortField.MAX_WICKETS_WITH_BOWLING_AVERAGE, maxWicketsComparator.thenComparing(ipl -> ipl.average));
    }

    public int loadIPLData(PlayerType playerType, String csvFilePath) throws IPLAnalyserException {
        iplAnalyserMap = IPLAdapterFactory.getIPLDataObject(playerType, csvFilePath);
        iplList = new ArrayList<>(iplAnalyserMap.values());
        return iplAnalyserMap.size();
    }

    public String getSortedData(SortField field, boolean reversed) throws IPLAnalyserException {
        if (iplList == null || iplList.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        iplList = new ArrayList<>(iplAnalyserMap.values());
        if (reversed) {
            this.sort(iplList, this.sortMap.get(field).reversed());
            return new Gson().toJson(iplList);
        }
        this.sort(iplList, this.sortMap.get(field));
        return new Gson().toJson(iplList);
    }

    private void sort(List<IPLAnalyserDAO> iplList, Comparator<IPLAnalyserDAO> iplComparator) {
        IntStream.range(0, iplList.size() - 1).flatMap(i -> IntStream.range(0, iplList.size() - i - 1)).forEachOrdered(j -> {
            IPLAnalyserDAO ipl1 = iplList.get(j);
            IPLAnalyserDAO ipl2 = iplList.get(j + 1);
            if (iplComparator.compare(ipl1, ipl2) > 0) {
                iplList.set(j, ipl2);
                iplList.set(j + 1, ipl1);
            }
        });
    }

    public enum PlayerType {BATSMAN, BOWLER}
}
