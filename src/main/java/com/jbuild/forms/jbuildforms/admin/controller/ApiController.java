package com.jbuild.forms.jbuildforms.admin.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jbuild.forms.jbuildforms.dao.DossierStatusDao;
import com.jbuild.forms.jbuildforms.dao.DossierStatusTypeDao;
import com.jbuild.forms.jbuildforms.dao.ProcessDossierStatusTypeDao;
import com.jbuild.forms.jbuildforms.model.DossierStatus;
import com.jbuild.forms.jbuildforms.model.DossierStatusType;
import com.jbuild.forms.jbuildforms.model.ProcessDossierStatusType;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	DossierStatusDao dossierStatusDao;
	
	@Autowired
	ProcessDossierStatusTypeDao processDossierStatusTypeDao;
	
	@Autowired
	DossierStatusTypeDao dossierStatusTypeDao;
	
	@GetMapping(value = "/template")
	public String  getTemplate() {
		
		return "";
	}
	/*
	 * Dossier Status
	 * */
	@GetMapping("/dossier-status")
	public List<DossierStatus> retrieveAllDstatus() {
		return (List<DossierStatus>) dossierStatusDao.findAll();
	}
	@GetMapping("/dossier-status/{id}")
	public DossierStatus retrieveStudent(@PathVariable int id) {
		Optional<DossierStatus> student = dossierStatusDao.findById(id);

		//if (!student.isPresent())
			//throw new StudentNotFoundException("id-" + id);

		return student.get();
	}
	@PostMapping("/dossier-status")
	public ResponseEntity<Object> createStudent(@RequestBody DossierStatus dossierStatus) {
		DossierStatus savedStudent = dossierStatusDao.save(dossierStatus);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	/*
	 *  Dossier status type
	 * */
	
	@GetMapping("/doss-sts-type")
	public List<DossierStatusType> retrieveAllDstatusType() {
		return (List<DossierStatusType>) dossierStatusTypeDao.findAll();
	}
	
	@GetMapping("/doss-sts-type/{id}")
	public  DossierStatusType retrieveDstatusType(@PathVariable int id) {
		Optional<DossierStatusType> dossierStatusType = dossierStatusTypeDao.findById(id);

		//if (!student.isPresent())
			//throw new StudentNotFoundException("id-" + id);

		return dossierStatusType.get();
	}
	
	@PostMapping("/doss-sts-type")
	public ResponseEntity<Object> createDstatusType(@RequestBody DossierStatusType dossierStatusType) {
		DossierStatusType savedPdossierStatus = dossierStatusTypeDao.save(dossierStatusType);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPdossierStatus.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	/*
	 * Procces dossier status type
	 * */
	
	@GetMapping("/proc-doss-sts-type")
	public List<ProcessDossierStatusType> retrieveAllPstatus() {
		return (List<ProcessDossierStatusType>) processDossierStatusTypeDao.findAll();
	}
	
	@GetMapping("/proc-doss-sts-type/{id}")
	public ProcessDossierStatusType retrieveProcessDossierStatusType(@PathVariable int id) {
		Optional<ProcessDossierStatusType> student = processDossierStatusTypeDao.findById(id);

		//if (!student.isPresent())
			//throw new StudentNotFoundException("id-" + id);

		return student.get();
	}
	
	@PostMapping("/proc-doss-sts-type")
	public ResponseEntity<Object> createStudent(@RequestBody ProcessDossierStatusType dossierStatus) {
		ProcessDossierStatusType savedPdossierStatus = processDossierStatusTypeDao.save(dossierStatus);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPdossierStatus.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
}
