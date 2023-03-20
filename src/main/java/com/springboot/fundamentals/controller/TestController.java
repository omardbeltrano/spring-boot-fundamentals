package com.springboot.fundamentals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping
	@ResponseBody
	//Ejemplo de de uso de un response y uso de la dependencia devtools desde el pom.xml
	public ResponseEntity<String> function(){
		return new ResponseEntity<>("Hello from controller", HttpStatus.OK);
	}
}
