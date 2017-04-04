package com.restapi.model;

public class FileMetadata {
  
    protected String fileId;  
    protected String fileName;  
    protected long fileSize;  
    
    public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long size) {
		this.fileSize = size;
	}

	public FileMetadata() {
        super();
    }
    
    public String getFileId() {
        return fileId;
    }
 
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
  
    public String getFileName() {
        return fileName;
    }
 
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}
