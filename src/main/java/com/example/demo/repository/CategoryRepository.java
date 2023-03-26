package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;

/**
 * categoriesテーブルにアクセスするためのインタフェース
 * @author tutor
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
