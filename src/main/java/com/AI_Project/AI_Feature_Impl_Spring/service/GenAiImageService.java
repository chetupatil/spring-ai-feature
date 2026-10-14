package com.AI_Project.AI_Feature_Impl_Spring.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class GenAiImageService {


	OpenAiImageModel imageModel;

	GenAiImageService(OpenAiImageModel imageModel){
		this.imageModel = imageModel;
	}

	public Path generateImages(String prompt) throws IOException {
		//ImageResponse response =  imageModel.call(new ImagePrompt(prompt));

		ImageResponse response =  imageModel.call(
				new ImagePrompt(prompt,
						OpenAiImageOptions.builder()
						.model("gpt-image-2.5-flare")
						.quality("high")
						.width(1024)
						.height(1024)
						.build()
						));
		Image image = response.getResult().getOutput();
		
		
		//List<String> imageUrl = response.getResults().stream().map(result -> result.getOutput().getUrl()).toList();
	
      
        Path imagePath = Path.of("generated-image.png");
        Files.write(imagePath, Base64.getDecoder().decode(image.getB64Json()));

        
		return imagePath;
	}
}