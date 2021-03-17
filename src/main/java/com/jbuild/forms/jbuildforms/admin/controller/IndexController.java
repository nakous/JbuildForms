package com.jbuild.forms.jbuildforms.admin.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbuild.forms.jbuildforms.model.Process;

import com.jbuild.forms.jbuildforms.dao.ProcessDao;

@Controller
@RequestMapping(value = "/admin")
public class IndexController {
	
	@Autowired
	ProcessDao processDao;
	
	@GetMapping(value = "/index")
	public String  getIndex(Model model,
			Locale locale) {
		Map<String,Object > attributes = new HashMap<String, Object>();
		List<Process> process = processDao.findAll();
		attributes.put("process", process);
		model.addAllAttributes(attributes);
		return "admin/index";
	}
	@GetMapping(value = "/process")
	public String  getProcess(Model model,
			@RequestParam(value = "processid", required = false) String processName,
			Locale locale) {
		Map<String,Object > attributes = new HashMap<String, Object>();
		Process process = processDao.findByName(processName);
		attributes.put("process", process);
		model.addAllAttributes(attributes);
		return "admin/process";
	}

}
