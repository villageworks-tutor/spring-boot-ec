package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

/**
 * ログインアカウントを管理するModel
 * @author tutor
 */
@Component
@SessionScope
@Data
public class Account {

	/**
	 * フィールド
	 */
	private String name;

	/**
	 * デフォルトコンストラクタ
	 */
	public Account() {
	}

}
