package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.utils.UserContextUsage;

@RestController
public class HelloWorldController {
@Autowired
UserContextUsage contextUsage;
	@RequestMapping({ "/hello" })
	public String firstPage() {
		contextUsage.functionUsageLogger("hello");
		return "Hello World";
	}

}