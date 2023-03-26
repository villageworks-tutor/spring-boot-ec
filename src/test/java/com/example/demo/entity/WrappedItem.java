package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class WrappedItem extends Item {
	
	/**
	 * デフォルトコンストラクタ
	 */
	public WrappedItem() {
	}

	/**
	 * コンストラクタ
	 * @param categoryId カテゴリーID
	 * @param name       商品名
	 * @param price      価格
	 */
	public WrappedItem(Integer categoryId, String name, Integer price) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}

	/**
	 * コンストラクタ
	 * @param categoryId カテゴリーID
	 * @param name       商品名
	 * @param price      価格
	 * @param qiantity   数量
	 */
	public WrappedItem(Integer categoryId, String name, Integer price, int quantity) {
		this(categoryId, name, price);
		this.quantity = quantity;
	}
	
	public WrappedItem(Integer id, Integer categoryId, String name, Integer price, int quantity) {
		this(categoryId, name, price, quantity);
		this.id = id;
	}
	

}
