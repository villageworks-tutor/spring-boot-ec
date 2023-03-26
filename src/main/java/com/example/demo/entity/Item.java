package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	private String name;
	private Integer price;

	/**
	 * デフォルトコンストラクタ
	 */
	public Item() {
	}

	/**
	 * コンストラクタ
	 * @param categoryId カテゴリーID
	 * @param name       商品名
	 * @param price      価格
	 */
	public Item(Integer categoryId, String name, Integer price) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}

}
