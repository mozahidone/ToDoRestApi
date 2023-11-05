package com.mozahidone.security.oauth2client.controller;

import com.mozahidone.security.oauth2client.config.WelcomeClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WelcomeController {
	
	private final WelcomeClient welcomeClient;
	
	@GetMapping("/")
	public String welcome() {
		
		String welcomeFirst = welcomeClient.getWelcomeFirst();		
		String welcomeSecond = welcomeClient.getWelcomeSecond();	
		return "<h1>" +  welcomeFirst + "</h1><h1>" +  welcomeSecond + "</h1>";
	}
	
}
