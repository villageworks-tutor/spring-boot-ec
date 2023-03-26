package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * cateoriesテーブルの1件分のレコードを管理するクラス
 * @author tutor
 */
@Entity
@Table(name = "categories")
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Category() {
	}

	/**
	 * コンストラクタ
	 * @param id   カテゴリーID
	 * @param name カテゴリー名
	 */
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

}
