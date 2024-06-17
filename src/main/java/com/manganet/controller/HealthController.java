package com.manganet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/hello-world")
public class HealthController {
	
	@GetMapping
	public String getStatus() {
		return "Hello World";
	}
	
}
