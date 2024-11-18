package com.example.navalbattleminiproject3.model.board.GameData;

public abstract class DataFileHandler {

    public void rewriteFile(String fileName, String content){};

    public void writeToFile(String fileName, String content){};

    public String[] readFromFile(String fileName){return new String[0];};

    public void serializeData(String fileName, String content){};

    public Object deserializeData(String fileName){return null;};
}
