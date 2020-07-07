package com.bridgelabz.iplanalyser.utility;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.models.IPLMostWicketsCSV;

import java.util.Map;

public class IPLBowlerAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLAnalyserDAO> loadIPLData(String csvFilePath) throws IPLAnalyserException {
        return super.loadIPLData(IPLMostWicketsCSV.class, csvFilePath);
    }
}
