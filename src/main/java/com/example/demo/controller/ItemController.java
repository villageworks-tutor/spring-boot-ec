package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/items")
	public String index(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		// カテゴリーリストを取得
		List<Category> categories = this.categoryRepository.findAll();
		// 取得したカテゴリーリストをリクエストスコープに登録
		model.addAttribute("categories", categories);
		
		// リクエストパラメータによる商品リスト取得処理の分岐
		List<Item> items = null;
		if (categoryId == null) {
			items = this.itemRepository.findAll();
		} else {
			items = this.itemRepository.findByCategoryId(categoryId);
		}
		
		// 取得した商品リストをリクエストスコープに登録
		model.addAttribute("items", items);
		// 商品一覧画面に遷移
		return "items";
	}

}
