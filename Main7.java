package com.koreait.crawling;

import java.util.List;

import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main7 {

	public static void main(String[] args) {
		String DRIVER_ID = "webdriver.chrome.driver";
		// 파일 절대경로 입력
		String DRIVER_PATH = "c:/MINJI/JSP/chromedriver.exe";
		
		// 크롬드라이버 연결
		System.setProperty(DRIVER_ID, DRIVER_PATH);	
		// 크롬드라이버 객체 생성
		WebDriver driver = new ChromeDriver();
				
		// 크롤링할 주소를 base_url 변수에 저장
		String base_url = "https://www.banapresso.com/store";

		
		try {
			// 드라이버를 통해 크롤링할 주소가 담긴 변수를 가져옴
			driver.get(base_url);
			
			while (true) {
				// 데이터를 출력시킬 때 1초에 한번씩 끊기
				Thread.sleep(1000);
				// 크롬드라이버 객체를 통해 store_name_map 이름과 일치하는 요소를 찾아 가져와 리스트 elements 변수 안에 하나씩 저장 
				List<WebElement> elements = driver.findElements(By.className("store_name_map"));
				
				
				/*
				 	ORIGINAL PINK
					교대점
					서울 서초구 서초대로54길 27, 1층
				 */
				
				
				// elements에 차곡차곡 저장된 데이터가 존재하는 동안 반복을 돌면서 데이터를 하나씩 꺼내 el 변수에 담고
				for (WebElement el : elements) {
					// el 변수에 담긴 데이터를 줄바꿈 단위로 나눠서 가져온 다음, store 변수에 나눠진 데이터를 배열에 순서대로 저장
					String[] store = el.getText().split("\n");
					// 지점명과 주소만 출력하기 위해 index 0에 담겨있는 데이터는 출력시키지 않고
					// index 1번에 담겨있는 지점명을 출력
					System.out.println(store[1]);
					// index 2번에 담겨있는 주소를 출력
					System.out.println(store[2]);
					System.out.println("---------------------");
					
					
					/*
						교대점
						서울 서초구 서초대로54길 27, 1층
					 */
					
					
				}
				
				int num = 2;
				
				// 2(num)을 6으로 나눈 나머지가 0일 경우
				if (num % 6 == 0 ) {
					try {
						// 예외처리를 위해 try문 안에 작성
						List<WebElement> nextPage = driver.findElements(By.className("store_name_map"));
						
						for(WebElement el : nextPage) {
							if (el.getText().equals("다음")) {
								el.click();
								num = 2;
								break;
							}
						}
					} catch (Exception e) {
						break;
					}
				} else {
					WebElement nextPage = driver.findElement(By.cssSelector("div.pagination>ul>li:nth-child("+num+")>a"));
					nextPage.click();
					num++;
				}
			}
		} catch (Exception e) {
			System.out.println("프로그램 종료");
		}
	}
}
