package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

/**
 * カートを制御するmodel
 * @author tutor
 */
@Component
@SessionScope
public class Cart {
	
	/**
	 * フィールド：商品リスト
	 */
	List<Item> itemList = new ArrayList<>();

	/**
	 * 商品リストを取得する
	 * @return 商品リスト
	 */
	public List<Item> getItems() {
		return itemList;
	}

	/**
	 * 商品をカートに追加する
	 * @param newItem 追加する商品
	 */
	public void add(Item newItem) {
		// すでにカートにある商品かどうかを判定
		Item existItem = null;
		for (Item currentItem : this.itemList) {
			if (currentItem.getId() == newItem.getId()) {
				existItem = currentItem;
				break;
			}
		}
		// すでにカートにある商品かどうかで処理を分岐
		if (existItem == null) {
			// カートにない商品の場合：新規追加
			this.itemList.add(newItem);
		} else {
			// カートにある種品の場合：当該商品の数量を加算
			int quantity = existItem.getQuantity() + newItem.getQuantity();
			existItem.setQuantity(quantity);
		}
	}

	/**
	 * 商品の合計金額を取得する
	 * @return 商品の合計金額
	 */
	public Integer getTotalPrice() {
		Integer total = 0;
		for (Item item : this.itemList) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	}
	
	/**
	 * 商品を削除する
	 * @param itemId 削除する商品の商品ID
	 */
	public void delete(Integer itemId) {
		for (Item item : this.itemList) {
			if (item.getId() == itemId) {
				this.itemList.remove(item);
				break;
			}
		}
	}

}
