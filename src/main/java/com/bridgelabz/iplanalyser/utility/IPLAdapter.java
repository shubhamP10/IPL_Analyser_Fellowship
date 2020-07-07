package com.bridgelabz.iplanalyser.utility;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;

import java.util.Map;

public abstract class IPLAdapter {

    public abstract Map<String, IPLAnalyserDAO> loadIPLData(String... csvFilePath)
            throws IPLAnalyserException;


    public <E> Map<String, IPLAnalyserDAO> loadIPLData(Class<E> iplMostRunsCSVClass, String... csvFilePath) {
        return null;
    }
}
