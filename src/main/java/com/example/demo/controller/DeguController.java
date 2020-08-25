package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.form.DeguForm;

import com.example.demo.tryDegu.Degu;
import com.example.demo.tryDegu.DeguService;



@Controller
public class DeguController {
	@Autowired
	private DeguService deguService; 
	@PostMapping("/DeguForm")
	public String getsignup(@ModelAttribute DeguForm form,Model model) {
	return "DeguForm";
	}
	@GetMapping("/DeguConfirm")
	public String getdone(@ModelAttribute DeguForm form,Model model) {
		System.out.print(form);
	return "DeguConfirm";
	}
	@GetMapping("/DeguTop")
	public String gettop(@ModelAttribute DeguForm form,Model model) {
		List<Degu>deguList=deguService.selectMany();
		model.addAttribute("deguList",deguList);
		List<String>yearmonth=deguService.selectDistinctYearMonth();
		model.addAttribute("yearmonth", yearmonth);
		System.out.print(form);
	return "DeguTop";
	}
	@PostMapping("/DeguRegister")
	public String getregister(@ModelAttribute DeguForm form,Model model) {
		System.out.print(form);
		Degu degu=new Degu();
		
		degu.setComment(form.getComment());
		degu.setPhoto(form.getPhoto());
		boolean result=deguService.insert(degu);
		if(result==true) {
			System.out.println("insert 成功");
		}else {
			System.out.println("insert 失敗");
		}
		return "redirect:/DeguTop";
	}
	@GetMapping("/DeguDelete/{id:.+}")
	public String getDeguDetail(Model model,@PathVariable("id")int Id)
	{
		DeguForm form=new DeguForm();
			Degu degu=deguService.selectOne(Id);
			form.setId(degu.getId());
			form.setComment(degu.getComment());
			form.setPhoto(degu.getPhoto());
			model.addAttribute("deguForm",form);
	
			return "DeguDelete";
}
	@PostMapping(value="/deguDetail")
	public String postDeguDetailDelete(@ModelAttribute DeguForm form,Model model) {
	boolean result=deguService.deleteOne(form.getId());
	if(result==true) {
		model.addAttribute("result","削除成功");
	}else {
		model.addAttribute("result","削除失敗");
	}
	return "redirect:/DeguTop";
	}
	
	@GetMapping("/DeguUpdate/{id:.+}")
	public String getDeguUpdate(Model model,@PathVariable("id")int Id)
	{
		DeguForm form=new DeguForm();
			Degu degu=deguService.selectOne(Id);
			form.setId(degu.getId());
			form.setComment(degu.getComment());
			form.setPhoto(degu.getPhoto());
			model.addAttribute("deguForm",form);
	
			return "DeguUpdate";
	}
@PostMapping(value="/DeguUpdate")
public String postDeguUpdate(@ModelAttribute DeguForm form, Model model) {
	System.out.println("更新ボタンの処理");
	Degu degu=new Degu();
	degu.setId(form.getId());
	degu.setComment(form.getComment());
	degu.setPhoto(form.getPhoto());
	boolean result=deguService.updateOne(degu);
	if(result==true) { 
		System.out.println("更新成功");
	}else {
		System.out.println("更新失敗");
	}
	return "redirect:/DeguTop";
}
@GetMapping("/DeguArchive/{year}/{month}")
public String getArchive(Model model,@PathVariable("year")int year,@PathVariable("month")int month)
{
	
		List<Degu> deguList=deguService.selectByYearMonth(year,month);
		model.addAttribute("deguList",deguList);
		System.out.println(deguList);
		List<String>yearmonth=deguService.selectDistinctYearMonth();
		model.addAttribute("yearmonth", yearmonth);

		return "DeguArchive";
}

}
	
//@GetMapping("/deguList")
//public String getDeguList(Model model) {
//	//List<Degu>deguList=deguService.selectMany();
//	//model.addAttribute("deguList",deguList);
//	Degu degu= deguService.selectOne(2);
//	model.addAttribute("degu",degu);
//	
//	return "DeguTop";
//	}

	
	
	