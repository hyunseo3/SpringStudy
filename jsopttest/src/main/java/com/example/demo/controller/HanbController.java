package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.Book;

import lombok.Setter;

@RestController
@Setter
public class HanbController {
	
    @GetMapping("/newBook")
    public ArrayList<Book> newBook() {
    	ArrayList<Book> bookList = new ArrayList<Book>();
        String url = "https://www.hanbit.co.kr/store/books/new_book_list.html";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements list = doc.getElementsByClass("book_tit");
            for (Element e : list) {
                Element a = e.getElementsByTag("a").get(0); // a 태그를 찾아줘
                String title = a.text();
                String link = "https://www.hanbit.co.kr/" + a.attr("href"); // a태그의 속성 중 href를 가져와줘
                bookList.add(new Book(title, link));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bookList;
    }
    
    @GetMapping("/seat")
    public String seat(){
       	String seatVal = null;
       	String url="http://mpllc-seat.sen.go.kr/seatinfo/Seat_Info/1_count.asp";
       	try {
			Document doc=Jsoup.connect(url).get();
			Element e= doc.getElementsByClass("wating_f").get(0);
			seatVal=e.text();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
       	
       	return seatVal;
    }
    @GetMapping("/downImg")
    public String downImg() {
    	String addr="https://www.hanbit.co.kr/data/books/B8406119618_m.jpg";
    	String fname="숨은그림찾기.jpg";
    	try {
			URL url=new URL(addr);
			InputStream is=url.openStream();
			FileOutputStream fos=new FileOutputStream("c:/data/"+fname);
			FileCopyUtils.copy(is.readAllBytes(), fos);
			is.close();
			fos.close();
			System.out.println("이미지를 다운로드하였습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return "OK";
    }
    
	@GetMapping("/allImgDown")
	public String allImgDown() {
		String addr = "https://www.hanbit.co.kr/store/books/new_book_list.html";
		String base = "https://www.hanbit.co.kr/";
		try {
			Document doc = Jsoup.connect(addr).get();
			Elements view_box_list= doc.getElementsByClass("view_box");
			for(Element view_box:view_box_list) {
				String src = view_box.getElementsByClass("thumb").get(0).attr("src");
				String fname = view_box.getElementsByClass("book_tit").get(0).getElementsByTag("a").get(0).html();
				System.out.println(src);
				System.out.println(fname);
				System.out.println("---------------------------------------------------");
				
				downloadImage(base+src, fname);			
			}
			System.out.println("모두 다운로드 하였습니다.");
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
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
