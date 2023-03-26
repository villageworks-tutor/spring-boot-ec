package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
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
	
	/**
	 * カートに商品を追加する
	 * @param itemId   追加する商品の商品ID
	 * @param quantity 追加する数量
	 * @param model    リクエストスコープ
	 * @return URL「/chart」に遷移
	 */
	@PostMapping("/cart/add")
	public String addItem(
			@RequestParam(name = "itemId", defaultValue = "") Integer itemId,
			@RequestParam(name = "quantity", defaultValue = "1") Integer quantity,
			Model model) {
		
		// 追加商品の取得
		Item item = itemRepository.findById(itemId).get();
		// 購入数量を設定
		item.setQuantity(quantity);
		// 商品をカートに追加
		cart.add(item);
		
		// カート画面に遷移
		return "redirect:/cart";
	}

	/**
	 * カートの商品を削除する
	 * @param itemId 削除する商品の商品ID
	 * @param model  リクエストスコープ
	 * @return URL「/chart」に遷移
	 */
	@PostMapping("/cart/delete")
	public String deleteItem(
			@RequestParam("itemId") Integer itemId,
			Model model) {
		
		// カート内の商品を削除
		cart.delete(itemId);
		
		// カート画面に遷移
		return "redirect:/cart";
	}
	
}
