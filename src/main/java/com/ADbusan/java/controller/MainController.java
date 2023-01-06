package com.ADbusan.java.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ADbusan.java.dto.HelloDto;
import com.ADbusan.java.dto.reponse.ResponseDto;
//Response로 HTML을 반환하는 Controller가 아니 
//Response Body에 직접 데이터를 담아서 응답하는 Controller
//@Controller + @ResponseBody
@RestController
//RequestMapping(pattern):http://localhost:4040/(end-point)/~~~
//end-point의 패턴을 지정하여 해당 패턴의 end-point 일 때 해당 controller를 실행
@RequestMapping("apis/")
public class MainController {
	static final String HELLO = "hello";
//	https방식://호스트:포트/~(/~:end-point)
//	http://localhost:4040/~~~
//	@GetMapping(end-point):해당 end-point로 get 방식의 request가 왔을 때 동작
	@GetMapping("")
	public String hello() {
		return "Hello Spring Boot world";
	}
	@GetMapping(HELLO)
//	@RequestParam(name="", value="", required=true, defaultValue="")
//	: URL로 데이터를 받는 경우(get, delete) 쿼리 형태로 데이터를 받음 
//	http://호스트:포트/end-point?name=value&...
	public String getHello(@RequestParam(name="name",required=false, defaultValue="james")String name) {
		return "This is get method, end-point '/hello'" + name;
	}
	
//	@PathVariable(path) : URL 데이터를 받는 경우(Get, Delete) path 형태로 데이터를 받음
//	http://호스트:포트/end-point/VARIABLE
	@GetMapping(HELLO + "/{name}/spring")
	public String getHelloName(@PathVariable("name")String name) {
		return "This is get method, end-point '/hello'"+ name;
	}
	
//	@PostMapping(end-point):해당 end-point로 Post 방식의 Request가 왔을 때 동작
	@PostMapping(HELLO)
//	@requestbody : 해당 request의 body에서 json을 인색해 인스턴스로 변경 
	public ResponseDto<HelloDto> postHello(@RequestBody HelloDto requestBody) {
//		return requestBody.toString();
		return ResponseDto.setSuccess("hello", requestBody);
				
	}
	
//	@PutMapping(end-point): 해당 end-point로 put 방식의 request가 왔을 때 동작
	@PutMapping("hello")
	public String putHello() {
		return "This is put method, end-point '/hello!!!'";
	}
	
//	@patchMapping(end-point) : 해당 end-point로 patch 방식의 request가 왔을 때 동작
	@PatchMapping("hello")
	public String patchHello() {
		return "This is patch method, end-point '/hello!!!'";
	}
	
//	@deleteMapping(end-point) : 해당 end-point로 delete 방식의 request가 왔을 때 동작
	@DeleteMapping("hello")
	public String deleteHello() {
		return "This is delete method, end-point '/hello!!!'";
	}
}








