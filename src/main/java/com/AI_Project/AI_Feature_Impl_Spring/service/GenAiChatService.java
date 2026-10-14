package com.AI_Project.AI_Feature_Impl_Spring.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class GenAiChatService {
	
	private ChatModel chatModel;
	
	GenAiChatService(ChatModel chatModel){
		this.chatModel = chatModel;
	}
	
	
	public String callAi(String prompt) {
		return chatModel.call(prompt);
	}
	
	
	public String callAiOption(String prompt) {
		ChatResponse cmodel =  chatModel.call(new Prompt(prompt,OpenAiChatOptions.builder()
				.model("gpt-4")
				.temperature((double) 0.4F)
				.build()));
		
		return cmodel.getResult().getOutput().getText();
		
	}
	

}
