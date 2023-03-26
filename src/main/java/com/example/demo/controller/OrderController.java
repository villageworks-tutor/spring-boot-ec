package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

	/**
	 * 顧客情報入力画面を表示する
	 * @return 顧客情報入力画面ファイルベース名
	 */
	@GetMapping("/order")
	public String index() {
		return "customerForm";
	}

}
