package com.bridgelabz.iplanalyser.utility;

import com.bridgelabz.iplanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplanalyser.models.IPLAnalyserDAO;
import com.bridgelabz.iplanalyser.models.IPLMostRunsCSV;
import com.bridgelabz.iplanalyser.models.IPLMostWicketsCSV;
import com.bridgelabz.opencsvbuilder.CSVBuilderException;
import com.bridgelabz.opencsvbuilder.CSVBuilderFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {

    public abstract Map<String, IPLAnalyserDAO> loadIPLData(String csvFilePath)
            throws IPLAnalyserException;


    public <E> Map<String, IPLAnalyserDAO> loadIPLData(Class<E> IPLCSVClass, String csvFilePath) throws IPLAnalyserException {
        Map<String, IPLAnalyserDAO> iplMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<E> iplIterator = CSVBuilderFactory.createCSVBuilder().getOpenCSVFileIterator(reader, IPLCSVClass);
            Iterable<E> iplCSV = () -> iplIterator;
            String className = IPLCSVClass.getSimpleName();
            char ignoreChar = '-';
            switch (className) {
                case "IPLMostRunsCSV":
                    StreamSupport.stream(iplCSV.spliterator(), false)
                            .map(IPLMostRunsCSV.class::cast)
                            .forEach(iplCSVObj -> iplMap.put(iplCSVObj.player, new IPLAnalyserDAO(iplCSVObj)));
                    break;
                case "IPLMostWicketsCSV" :
                    StreamSupport.stream(iplCSV.spliterator(), false)
                            .map(IPLMostWicketsCSV.class::cast)
                            .forEach(iplCSVObj -> iplMap.put(iplCSVObj.player, new IPLAnalyserDAO(iplCSVObj)));
                    break;
            }
            return iplMap;
        } catch (IOException e) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.type.name(), e.getMessage());
        }
    }
}
