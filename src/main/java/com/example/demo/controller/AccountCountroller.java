package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

import jakarta.servlet.http.HttpSession;

/**
 * ログイン処理を制御するController
 * @author tutor
 */
@Controller
public class AccountCountroller {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Account account;

	/**
	 * ログイン画面を表示する
	 * @return ログイン画面ファイルベース名
	 */
	@GetMapping({"/", "/login", "/logout"})
	public String index() {
		// セッションスコープに登録されているログイン情報を破棄
		session.invalidate();
		// ログイン画面に遷移
		return "login";
	}
	
	/**
	 * ログイン処理を実行する
	 * @param name  ログインアカウント名
	 * @param model リクエストスコープ
	 * @return ログインに成功した場合は商品一覧画面ファイルベース名、それ以外はログイン画面ファイルベース名
	 */
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "name", defaultValue = "") String name,
			Model model) {
		
		// 送信データの判定
		if (name == null || name.isEmpty()) {
			// アカウント名が入力されていない場合：ログイン画面に戻る
			model.addAttribute("message", "アカウント名を入力してください。");
			return "login";
		}
		
		// ログインアカウント名をセッションスコープのログイン情報に設定
		account.setName(name);
		
		// URL「/items」に遷移：カテゴリーリスト、商品リストの取得処理を丸投げする
		return "redirect:/items";
	}

}
