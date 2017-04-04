package com.restapi.controller;


import com.restapi.model.FileData;
import com.restapi.model.FileMetadata;
import com.restapi.service.FileManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "filemetadata/v1")
public class FileManagerController {
 
	@Autowired
	@Qualifier("FileManagerService")
	FileManagerService filemngrservice;
	 
    private Logger logger = LoggerFactory.getLogger(FileManagerController.class);
    
    @RequestMapping(value = {"/healthcheck"})
    public Object sampleRequest() {
        return "hello...message from filemanger api ";
    }

    /**
     * upload a file     
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST,consumes = "multipart/form-data")
    public Object uploadFile(@RequestParam(value = "file", required = true) MultipartFile inputFile) throws Exception {
        
    	logger.info("input file name : "+inputFile.getOriginalFilename());
        FileData file = new FileData();
        file.setFileData(inputFile.getBytes());
        file.setFileId(UUID.randomUUID().toString());
        file.setFileName(inputFile.getOriginalFilename());
        file.setFileSize(inputFile.getSize());
        
        filemngrservice.upload(file);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<String>("file uploaded successfully", httpHeaders, HttpStatus.OK);

    }
    /**
     * Returns file meta data     
     *
     */
    @RequestMapping(value = "/metadata", method = RequestMethod.GET)
    public HttpEntity<List<FileMetadata>> getFileMetadata(@RequestParam(value = "filename", required = true) String fileName) throws Exception {
    	logger.info("input file name : "+fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<List<FileMetadata>>(filemngrservice.findFile(fileName), httpHeaders, HttpStatus.OK);
    }
    }

