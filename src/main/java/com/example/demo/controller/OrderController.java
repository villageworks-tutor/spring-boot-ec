package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;

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
	
	/**
	 * 顧客情報入力確認画面を表示する
	 * @return 顧客呪法確認画面ファイルベース名
	 */
	@PostMapping("/order/confirm")
	public String confirm(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "email", defaultValue = "") String email,
			Model model) {
		
		// Customerクラスをインスタンス化
		Customer customer = new Customer(name, address, tel, email);
		// リクエストスコープに登録
		model.addAttribute("customer", customer);
		// 確認画面に遷移
		return "orderConfirm";
	}
	
	@PostMapping("/order")
	public String complete(
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			Model model) {
		
		
		return "ordered";
		
	}

}
