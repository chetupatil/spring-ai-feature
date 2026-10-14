package com.AI_Project.AI_Feature_Impl_Spring.service;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;


@Service
public class GenAiReceipeService {

	private final ChatModel chatModel;

	GenAiReceipeService(ChatModel chatModel){
		this.chatModel = chatModel;
	}


	public String receipeCreate(String ingredients, String cuisine) {
		String promptMessage = "I want to prepare food based on : {ingredients}."
				+ "The Cuisine type i prefer is : {cuisine}."
				+ "combining all the details, Give the title, list of ingredients and cooking instructions";
		PromptTemplate pTemplate = new PromptTemplate(promptMessage);
		Map<String,Object> map = Map.of(
				"ingredients" , ingredients,
				"cuisine",cuisine
				);
		Prompt prompt = pTemplate.create(map);
		return chatModel.call(prompt).getResult().getOutput().getText();

	}


}
