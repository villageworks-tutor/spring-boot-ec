package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Account;

@Aspect
@Component
public class CheckLogingAspect {

	@Autowired
	Account account;
	
	/**
	 * ログを出力する：全てのControllerクラス（*Controller）の全てのメソッド処理（*Controller.*(..)）の前に実行される
	 * @param joinPoint
	 */
	@Before("execution(* com.example.demo.controller.*Controller.*(..))")
	public void writeLog(JoinPoint joinPoint) {
		// ログインしたアカウントで表示を切り替え
		if (account == null || account.getName() == null || account.getName().isEmpty()) {
			// アカウント情報がない場合
			System.out.print("ゲスト：");
		} else {
			// アカウント情報がある場合
			System.out.print(account.getName() + "：");
		}
		System.out.println(joinPoint.getSignature());
	}

	/**
	 * ログインしているかどうかをチェックする：リダイレクト先はログインページ
	 * @param joinPoint
	 * @return 未ログインの場合にはログインページにリダイレクト、それ以外はController内の呼び出し元メソッドを続行する
	 * @throws Throwable
	 */
	@Around("execution(* com.example.demo.controller.ItemController.*(..)) || "
		  + "execution(* com.example.demo.controller.CartController.*(..)) || "
		  + "execution(* com.example.demo.controller.OrderController.*(..))")
	public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
		if (account == null || account.getName() == null || account.getName().isEmpty()) {
			System.out.println("ログインしていません。");
			return "redirect:/login?error=notLoggedIn";
		}
		// Controller内のメソッドを実行
		return joinPoint.proceed();
	}

}
