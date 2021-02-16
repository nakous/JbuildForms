package com.jbuild.forms.jbuildforms.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jbuild.forms.jbuildforms.dao.ProcessDao;
import com.jbuild.forms.jbuildforms.model.Process;

@Controller
@ConditionalOnExpression("${controller.enabled:true}")
public class QuestionController  {

	@Autowired
	ProcessDao processDao;

	@GetMapping("/test")
	 Process  getTest() {
		//ModelAndView mav = new ModelAndView("Questions");
		Process  process= new Process();
		
		process.setName("MODULE");
		process.setPrefix("process.module");
		//Process  process2= processDao.save(process);
		
		return process;
		
	} 
	
	@GetMapping("/question")
	public String  getProcess( Model model) {
		ModelAndView mav = new ModelAndView("qxuestions");
		Map<String,Object > attributes = new HashMap<String, Object>();
		
		
		Process  process= new Process();
		
		process.setName("MODULE");
		process.setPrefix("process.module");
		//Process  process2= processDao.save(process);
		attributes.put("process", process);
		model.addAllAttributes(attributes);
		return "questions";
		
	} 
}
