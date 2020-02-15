package com.example.demo.app.inquiry;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryService;

/*
 * 
 */
@Controller //このクラスがコントローラーであることを示してあげる
@RequestMapping("/inquiry") //このコントローラーを訪れるためのurlを指定する
public class InquiryController {
// 	private final InquiryServiceImpl inquiryService;
	//Add an annotation here 
// 	public InquiryController(InquiryServiceImpl inquiryService){
// 		this.inquiryService = inquiryService;
// 	}
	private final InquiryService inquiryService;
	
	@Autowired
	public InquiryController(InquiryService inquiryService) {
		this.inquiryService = inquiryService;
	}
	
	@GetMapping //一覧の表示
	public String index(Model model) {
		//hands-on
		List<Inquiry> list = inquiryService.getAll();
		model.addAttribute("inquiryList", list);
		model.addAttribute("title", "Inquiry Index");
		
		return "inquiry/index";
	}
	
	@GetMapping("/form") //"/form"でこのメソッドにアクセスしてくることになる
	public String form(InquiryForm inquiryForm, Model model 
						,@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form";
	}
	
	@PostMapping("/form") 
	public String formGoBack(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "InquiryForm");
		return "inquiry/form";
	}
	
	
	@PostMapping("/confirm") //postでデータを送信されたときの処理
	public String confirm(@Validated InquiryForm inquiryForm,
										BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/form";
		}
		model.addAttribute("title", "Confirm Page");

		//hands-on
		
		return "inquiry/confirm";
	}
	
	@PostMapping("/complete")
	public String complete(@Validated InquiryForm inquiryForm, BindingResult result, Model model,
									RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "InquiryForm");
			return "inquiry/form";
		}
		//hands-on
		//completeしたのでデータベースに書き込む
		Inquiry inquiry = new Inquiry();
		inquiry.setName(inquiryForm.getName());
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setContents(inquiryForm.getContents());
		inquiry.setCreated(LocalDateTime.now());
		
		inquiryService.save(inquiry);
		//redirect
		redirectAttributes.addAttribute("complete", "Registered!");
		return "redirect:/inquiry/form"; //URLを指している
	}
	
}
