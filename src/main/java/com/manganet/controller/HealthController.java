package com.manganet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/mangas")
public class HealthController {
	
	@GetMapping("/hello-world")
	public String getStatus() {
		return "Hello World";
	}
	

}
