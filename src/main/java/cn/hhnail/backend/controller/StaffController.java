package cn.hhnail.backend.controller;

import cn.hhnail.backend.vo.response.AppResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vapi")
public class StaffController {

	@PostMapping("/login")
	public AppResponse<String> login(){

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return AppResponse.ok(null);
	}

}
