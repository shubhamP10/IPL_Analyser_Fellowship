package com.bridgelabz.iplanalyser.utility;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.models.IPLMostRunsCSV;

import java.util.Map;

public class IPLBatsmanAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLAnalyserDAO> loadIPLData(String csvFilePath) throws IPLAnalyserException {
        return super.loadIPLData(IPLMostRunsCSV.class,csvFilePath);
    }
}
