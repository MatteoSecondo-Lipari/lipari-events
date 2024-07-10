package com.lipari.events.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lipari.events.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	
	private final String UPLOAD_DIR = "uploads/";

	@Override
	public String saveImage(MultipartFile file) throws IOException {
		String newFilename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		
		Path filePath = Paths.get(UPLOAD_DIR + newFilename);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        
        return Paths.get(newFilename).toString();
	}

	@Override
	public Path getImagePath(String filename) {
		return Paths.get(UPLOAD_DIR + filename).toAbsolutePath().normalize();
	}

	@Override
	public byte[] getImage(String filename) throws IOException {
		Path filePath = Paths.get(UPLOAD_DIR + filename);
        return Files.readAllBytes(filePath);
	}

}
