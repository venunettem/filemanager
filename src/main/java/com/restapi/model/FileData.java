package com.restapi.model;


public class FileData extends FileMetadata {

  
    private byte[] fileData;    
    private String filePath;

  
    public FileData() {
    }   
    
    public byte[] getFileData() {
        return fileData;
    }
    
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFilePath() {
        return filePath;
    }
   public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
