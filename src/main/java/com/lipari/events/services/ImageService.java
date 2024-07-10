package com.lipari.events.services;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	public String saveImage(MultipartFile file) throws IOException;
	
	public Path getImagePath(String filename);
	
	public byte[] getImage(String filename) throws IOException;
}
