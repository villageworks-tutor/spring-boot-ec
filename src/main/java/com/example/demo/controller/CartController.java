package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Cart;
import com.example.demo.repository.ItemRepository;

/**
 * カートを制御するController
 * @author tutor
 */
@Controller
public class CartController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	Cart cart;

	/**
	 * カート画面を表示する
	 * @return カート画面ファイルベース名
	 */
	@GetMapping("/cart")
	public String index() {
		return "cart";
	}
	
}
