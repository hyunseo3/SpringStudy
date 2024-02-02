package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebtoonController {

	@GetMapping("/saveImg")
	public String saveImg() {
		String addr = "https://shared-comic.pstatic.net/thumb/webtoon/769209/thumbnail/thumbnail_IMAG21_3511dcdd-6e33-4171-8839-598d6d266215.jpg";
		try {
			String path = "c:/data";
			URL url = new URL(addr);
			InputStream is = url.openStream();
			System.out.println("is: " + is);
			String fname = "화산귀환.jpg";
			FileOutputStream fos = new FileOutputStream(path + "/" + fname);
			FileCopyUtils.copy(is.readAllBytes(), fos);
			is.close();
			fos.close();
			System.out.println("파일저장");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "OK";
	}

	@GetMapping("/allImageDown")
	public String allImageDown() {
		String url = "https://comic.naver.com/webtoon/weekday";
		try {
			Document doc = Jsoup.connect(url).get();
			//#container > div.component_wrap.type2 > div.WeekdayMainView__daily_all_wrap--UvRFc
			Elements list = doc.select("#content > div.list_area.daily_all");
			System.out.println(list);
			for (Element img : list) {
				String src = img.attr("src");
				String title = img.attr("title");
				System.out.println("src "+src);
				System.out.println("title "+title);
				System.out.println("-----------------------------");
				//downloadImage(src, title);
			}
			System.out.println("모두 다운로드하였습니다.");

		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
		return "OK";
	}

	public void downloadImage(String addr, String fname) {
		try {
			String path = "c:/data";

			fname = fname.replace("?", "");
			fname = fname.replace(":", "");

			FileOutputStream fos = new FileOutputStream(path + "/" + fname + ".jpg");
			URL url = new URL(addr);
			InputStream is = url.openStream();
			FileCopyUtils.copy(is.readAllBytes(), fos);
			fos.close();
			System.out.println(fname + "을 저장하였습니다.");
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
	}
}
