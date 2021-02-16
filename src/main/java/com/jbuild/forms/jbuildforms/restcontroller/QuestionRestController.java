package com.jbuild.forms.jbuildforms.restcontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbuild.forms.jbuildforms.dao.ProcessDao;
import com.jbuild.forms.jbuildforms.model.Process;


@RestController
@ConditionalOnExpression("${rest.enabled:true}")
@RequestMapping("api")
public class QuestionRestController {

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
