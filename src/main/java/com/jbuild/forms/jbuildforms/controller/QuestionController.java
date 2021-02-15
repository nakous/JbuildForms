package com.jbuild.forms.jbuildforms.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbuild.forms.jbuildforms.dao.ProcessDao;
import com.jbuild.forms.jbuildforms.model.Process;

@RestController
@ConditionalOnExpression("${controller.enabled:true}")
public class QuestionController  {

	@Autowired
	ProcessDao processDao;
	
	//@ConditionalOnProperty("controller.enabled")
	
	@GetMapping("/process")
	Process getProcess() {
		
		Process  process= new Process();
		
		process.setName("MODULE");
		process.setPrefix("process.module");
		//Process  process2= processDao.save(process);
		
		return process;
		
	} 
}
