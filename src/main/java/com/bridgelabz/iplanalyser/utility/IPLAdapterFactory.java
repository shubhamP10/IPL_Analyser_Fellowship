package com.bridgelabz.iplanalyser.utility;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.services.IPLAnalyser;

import java.util.Map;

public class IPLAdapterFactory {
    public static Map<String, IPLAnalyserDAO> getIPLDataObject(IPLAnalyser.PlayerType playerType,
                                                                  String... csvFilePath) throws IPLAnalyserException {
        if (playerType.equals(IPLAnalyser.PlayerType.BATSMAN))
            return new IPLBatsmanAdapter().loadIPLData(csvFilePath);
        return new IPLBowlerAdapter().loadIPLData(csvFilePath);
    }
}
