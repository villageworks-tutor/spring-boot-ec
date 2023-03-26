package com.example.demo.model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.demo.entity.Item;
import com.example.demo.entity.WrappedItem;

class CartTest {
	
	/** テスト対象クラス：system under test */
	Cart sut;
	
	@BeforeEach
	void setUp() {
		sut = new Cart();
	}
	
	@Nested
	@DisplayName("Test-02：Cart#getTotalPrice()メソッドのテストクラス")
	class TestGetTotalPrice {
		@ParameterizedTest
		@MethodSource("methodSource")
		@DisplayName("TestCase-21：商品をカートに入れるテストケース")
		void test01(List<Item> targets, Integer expected) {
			// execute
			for (Item target : targets) {
				sut.add(target);
			}
			// verify
			Integer actual = sut.getTotalPrice();
			assertThat(actual, is(expected));
		}
		
		
		static Stream<Arguments> methodSource() {
			
			List<Item> targetList = null;
			List<Integer> expectedList = null;
			
			List<List<Item>> targets = new ArrayList<>();
			List<List<Integer>> expected = new ArrayList<>();
			
			/**
			 * TestCase-21：商品ID「1」の商品を追加できる
			 */
			targetList = new ArrayList<>();
			expectedList = new ArrayList<>();

			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			expectedList.add(2500);
			
			targets.add(targetList);
			expected.add(expectedList);
			
			/**
			 * TestCase-22：商品ID「1」、商品ID「5」と商品ID「4」の商品を追加できる
			 */
			targetList = new ArrayList<>();
			expectedList = new ArrayList<>();
			
			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			targetList.add(new WrappedItem(5, 2, "The Racer", 1000, 1));
			targetList.add(new WrappedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			expectedList.add(5500);
			
			targets.add(targetList);
			expected.add(expectedList);
			
			/**
			 * TestCase-23：商品ID「1」、商品ID「5」と商品ID「4」の商品を追加したあとさらに商品ID「1」を追加できる
			 */
			targetList = new ArrayList<>();
			expectedList = new ArrayList<>();
			
			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			targetList.add(new WrappedItem(5, 2, "The Racer", 1000, 1));
			targetList.add(new WrappedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			expectedList.add(8000);
			
			targets.add(targetList);
			expected.add(expectedList);
			
			return Stream.of(
					  // TestCase-01
					  Arguments.of(targets.get(0), expected.get(0).get(0))
					  // TestCase-02
					, Arguments.of(targets.get(1), expected.get(1).get(0))
					  // TestCase-03
					, Arguments.of(targets.get(2), expected.get(2).get(0))
				);
		}
	}
	
	@Nested
	@DisplayName("Test-01：Cart#add(Item)メソッドのテストクラス")
	class TestAdd {
		@ParameterizedTest
		@MethodSource("methodSource")
		@DisplayName("TestCase-01：商品をカートに入れるテストケース")
		void test03(List<Item> targets, List<Item> expectedList) {
			// execute
			for (Item target : targets) {
				sut.add(target);
			}
			// verify
			if (sut.getItems().size() > 0) {
				for (int i = 0; i < sut.getItems().size(); i++) {
					Item actual = sut.getItems().get(i);
					Item expected = expectedList.get(i);
					assertThat(actual.getId(), is(expected.getId()));
					assertThat(actual.getCategoryId(), is(expected.getCategoryId()));
					assertThat(actual.getName(), is(expected.getName()));
					assertThat(actual.getPrice(), is(expected.getPrice()));
					assertThat(actual.getQuantity(), is(expected.getQuantity()));
				}
			} else {
				fail("テスト対象メソッドはまだ実装されていません。");
			}
		}
		static Stream<Arguments> methodSource() {
			
			List<Item> targetList = null;
			List<Item> expectedList = null;
			
			List<List<Item>> targets = new ArrayList<>();
			List<List<Item>> expected = new ArrayList<>();
			
			/**
			 * TestCase-11：商品ID「1」の商品を追加できる
			 */
			targetList = new ArrayList<>();
			expectedList = new ArrayList<>();

			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			expectedList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			
			targets.add(targetList);
			expected.add(targetList);
			
			/**
			 * TestCase-12：商品ID「1」、商品ID「5」と商品ID「4」の商品を追加できる
			 */
			targetList = new ArrayList<>();
			expectedList = new ArrayList<>();
			
			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			targetList.add(new WrappedItem(5, 2, "The Racer", 1000, 1));
			targetList.add(new WrappedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			expectedList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			expectedList.add(new WrappedItem(5, 2, "The Racer", 1000, 1));
			expectedList.add(new WrappedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			
			targets.add(targetList);
			expected.add(targetList);
			
			/**
			 * TestCase-13：商品ID「1」、商品ID「5」と商品ID「4」の商品を追加したあとさらに商品ID「1」を追加できる
			 */
			targetList = new ArrayList<>();
			expectedList = new ArrayList<>();
			
			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			targetList.add(new WrappedItem(5, 2, "The Racer", 1000, 1));
			targetList.add(new WrappedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			targetList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 1));
			expectedList.add(new WrappedItem(1, 1, "Javaの基本", 2500, 2));
			expectedList.add(new WrappedItem(5, 2, "The Racer", 1000, 1));
			expectedList.add(new WrappedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			
			targets.add(targetList);
			expected.add(expectedList);
			
			return Stream.of(
					  // TestCase-01
					  Arguments.of(targets.get(0), expected.get(0))
					  // TestCase-02
					, Arguments.of(targets.get(1), expected.get(1))
					  // TestCase-03
					, Arguments.of(targets.get(2), expected.get(2))
				);
		}
	}
	
}
