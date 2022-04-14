package cn.hhnail.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vapi")
public class DemoController {

	@PostMapping("/demoPost")
	public String demoPost(){
		return "demoPost success!";
	}

	@GetMapping("/demoGet")
	public String demoGet(){
		return "demoGet success!";
	}


}
