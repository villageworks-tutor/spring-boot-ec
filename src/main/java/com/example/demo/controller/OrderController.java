package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Cart;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class OrderController {
	
	@Autowired
	Cart cart;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderReposotpry;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
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
		
		// Customerクラスをインスタンス化
		Customer customer = new Customer(name, address, tel, email);
		// 顧客情報をcustomersテーブルに保存
		customerRepository.save(customer);
		
		//　注文情報をインスタンス化
		Order order = new Order();
		order.setCustomerId(customer.getId());
		order.setOrderedOn(LocalDate.now());
		order.setTotalPrice(cart.getTotalPrice());
		// 注文情報をordersテーブルに保存
		orderReposotpry.save(order);
		
		// 注文明細インスタンスのリストを生成
		List<OrderDetail> details = new ArrayList<>();
		for (Item item : cart.getItems()) {
			OrderDetail detail = new OrderDetail();
			detail.setOrderId(order.getId());
			detail.setItemId(item.getId());
			detail.setQuantity(item.getQuantity());
			details.add(detail);
		}
		
		// 注文明細をorder_detailsテーブルに保存
		orderDetailRepository.saveAll(details);
		
		// 注文番号をリクエストスコープに登録
		model.addAttribute("orderNumber", order.getId());
		
		// 注文完了画面に遷移
		return "ordered";
		
	}

}
