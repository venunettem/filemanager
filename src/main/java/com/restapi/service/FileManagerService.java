package com.restapi.service;

import com.restapi.model.FileData;
import com.restapi.model.FileMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Service("FileManagerService")
public class FileManagerService {

    private Logger logger = LoggerFactory.getLogger(FileManagerService.class);   
    public static final String UPLOAD_FOLDER = "C:/Users/Venu/Desktop/StoredFiles";  
  
    public void upload(FileData file) throws Exception {
         File uploadedFile = new File(UPLOAD_FOLDER);
         uploadedFile.mkdirs();
         createFileDirectory(file);
         String filePath = file.getFilePath() + "/" + file.getFileName();
         fileLoad(file, filePath);
         saveFileMetadata(file);
         }
      
    public void saveFileMetadata(FileData file) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("fileId", file.getFileId());
        properties.setProperty("fileName", file.getFileName());
        properties.setProperty("fileSize", String.valueOf(file.getFileSize()));
        if (file.getFilePath() != null) {
            File file1 = new File(new File(file.getFilePath()), "filemetadata.properties");

            OutputStream out = null;
            try {
                out = new FileOutputStream(file1);
                properties.store(out, "Meta data");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {             
                    
						out.close();	                
            }
        }
    }

   
    private void createFileDirectory(FileData file) {
    	String filePath = UPLOAD_FOLDER + "/";
        StringBuilder sb = new StringBuilder(filePath);
        String fileIdPath = sb.append(file.getFileId()).toString();
        file.setFilePath(fileIdPath);
        File fileDir = new File(fileIdPath);
        fileDir.mkdirs();
    }

    /**
     * Loads file data in the 'file store'
     *
     */
    private void fileLoad(FileData file, String filePath) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(file.getFileData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
        }
    }
    /**
     * Search a given input file by the file name.
     *  
     */
    public List<FileMetadata> findFile(String fileName) throws Exception {
        List<FileMetadata> fileMetadatas = new ArrayList<FileMetadata>();
        String path = UPLOAD_FOLDER + "/";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        InputStream inputStream = null;
        for (File file : listOfFiles) {
            if (!file.isHidden()) {
                File[] listOfFile = file.listFiles();
                for (File file1 : listOfFile) {
                    if (file1.getName().equals("filemetadata.properties")) {
                        Properties property = new Properties();                        
                            inputStream = new FileInputStream(file1);
                            property.load(inputStream);
                            if (property.containsValue(fileName)) {
                                FileMetadata fileMetadata = new FileMetadata();
                                fileMetadata.setFileId(property.getProperty("fileId"));
                                fileMetadata.setFileName(property.getProperty("fileName"));
                                fileMetadata.setFileSize(Long.parseLong(property.getProperty("fileSize")));
                                fileMetadatas.add(fileMetadata);
                            }                        
                    }
                }
            }
        }
        return fileMetadatas;
    }      
}
