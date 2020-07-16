package com.bridgelabz.iplanalyser.exception;

public class IPLAnalyserException extends Exception {

    public ExceptionType type;

    public IPLAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public IPLAnalyserException(String name, String message) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public enum ExceptionType {NO_DATA, PARSE_ERROR, IPL_FILE_PROBLEM}
}
