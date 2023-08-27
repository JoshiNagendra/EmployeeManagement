package in.joshi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ImageHelper {
	
	
	public String saveImageToStorage(byte[] imageBytes) {
	    try {
	        // Define the directory where you want to save images
	        String baseDirectory = "D:/employeedigital/images/";
	        
	        // Create the directory if it doesn't exist
	        File directory = new File(baseDirectory);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        // Generate a unique filename for the image (you can use any logic for this)
	        String filename = UUID.randomUUID().toString() + ".jpg";
	        String imagePath = baseDirectory + filename;

	        // Save the image bytes to the file
	        Files.write(Paths.get(imagePath), imageBytes);

	        return imagePath; // Return the path to the saved image
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Unknown"; // Handle the error appropriately
	    }
	}

}
