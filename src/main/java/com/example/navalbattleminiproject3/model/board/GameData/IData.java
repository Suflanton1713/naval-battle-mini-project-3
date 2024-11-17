package com.example.navalbattleminiproject3.model.board.GameData;

public interface IData{
    void rewriteFile(String fileName, String content);

    void writeToFile(String fileName, String content);

    String[] readFromFile(String fileName);

    void serializeData(String fileName, String content);

    Object deserializeData(String fileName);
}
