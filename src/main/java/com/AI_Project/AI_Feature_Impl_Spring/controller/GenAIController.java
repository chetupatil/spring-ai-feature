package com.AI_Project.AI_Feature_Impl_Spring.controller;



import java.io.IOException;
import java.nio.file.Path;

import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.AI_Project.AI_Feature_Impl_Spring.service.GenAiChatService;
import com.AI_Project.AI_Feature_Impl_Spring.service.GenAiImageService;
import com.AI_Project.AI_Feature_Impl_Spring.service.GenAiReceipeService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/openAi")
public class GenAIController {

	@Autowired
	GenAiChatService genAiChatService;

	@Autowired
	GenAiImageService genAiImageService;

	@Autowired
	GenAiReceipeService genAiReceipeService;


	@GetMapping("/v1/talkAI")
	public String talkToAi(@RequestParam String prompt) {
		return genAiChatService.callAi(prompt);
	}

	@GetMapping("/v2/talkAI")
	public String talkToAiOption(@RequestParam String prompt) {
		return genAiChatService.callAiOption(prompt);
	}

	@GetMapping("/v1/genImage")
	public void generateImage(HttpServletResponse res, @RequestParam String prompt) throws IOException {
		Path imageUrl = genAiImageService.generateImages(prompt);
		res.sendRedirect(imageUrl.toUri().toURL().getFile());	
	}

	@GetMapping("/v1/receipe-create")
	public String createReceipe(@RequestParam String ingredients,@RequestParam String cuisine) throws IOException {
		return genAiReceipeService.receipeCreate(ingredients, cuisine);
	}






}
