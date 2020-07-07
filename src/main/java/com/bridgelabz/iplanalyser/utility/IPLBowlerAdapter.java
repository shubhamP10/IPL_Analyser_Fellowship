package com.bridgelabz.iplanalyser.utility;

import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.models.IPLMostWicketsCSV;

import java.util.Map;

public class IPLBowlerAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLAnalyserDAO> loadIPLData(String... csvFilePath) {
        return super.loadIPLData(IPLMostWicketsCSV.class, csvFilePath);
    }
}
