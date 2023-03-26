package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

/**
 * itemsテーブルのアクセスするためのインタフェース
 * @author tutor
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {

	/**
	 * カテゴリー別の商品を検索する
	 * @param categoryId カテゴリーID
	 * @return カテゴリーに含まれる商品の商品リスト
	 */
	List<Item> findByCategoryId(Integer categoryId);
	
}
