package com.ADbusan.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ADbusan.java.dto.reponse.ResponseDto;
import com.ADbusan.java.dto.user.GetUserResponseDto;
import com.ADbusan.java.dto.user.PostUserDto;
import com.ADbusan.java.dto.user.PostUserResponseDto;
import com.ADbusan.java.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired UserService userService;
	@GetMapping("{email}")
	public ResponseDto<GetUserResponseDto> getUser(@PathVariable("email")String email){
		return userService.getUser(email);
	}
	@PostMapping("")
	public ResponseDto<PostUserResponseDto>postUser(@RequestBody PostUserDto requestBody){
		return userService.postUser(requestBody);
	}
	
}

