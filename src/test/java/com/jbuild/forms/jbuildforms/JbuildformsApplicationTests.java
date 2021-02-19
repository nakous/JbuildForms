package com.jbuild.forms.jbuildforms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.list.LazyList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import com.jbuild.forms.jbuildforms.dao.DossierDao;
import com.jbuild.forms.jbuildforms.dao.DossierStatusDao;
import com.jbuild.forms.jbuildforms.dao.DossierStatusTypeDao;
import com.jbuild.forms.jbuildforms.dao.LanguageDao;
import com.jbuild.forms.jbuildforms.dao.ProcessDao;
import com.jbuild.forms.jbuildforms.dao.ProcessStatusTypePropertyDao;
import com.jbuild.forms.jbuildforms.dao.RoleDao;
import com.jbuild.forms.jbuildforms.dao.UserDao;
import com.jbuild.forms.jbuildforms.enumeration.DossierResult;
import com.jbuild.forms.jbuildforms.model.Dossier;
import com.jbuild.forms.jbuildforms.model.DossierStatus;
import com.jbuild.forms.jbuildforms.model.DossierStatusType;
import com.jbuild.forms.jbuildforms.model.Process;
import com.jbuild.forms.jbuildforms.model.tools.Language;
import com.jbuild.forms.jbuildforms.model.ProcessStatusTypeProperty;
import com.jbuild.forms.jbuildforms.model.Role;
import com.jbuild.forms.jbuildforms.model.User;

@SpringBootTest
class JbuildformsApplicationTests {

	@Autowired
	DossierDao dossierDao;
	
	@Autowired
	ProcessDao processDao;
	
	@Autowired
	LanguageDao languageDao;
	
	@Autowired
	DossierStatusDao dossierStatusDao;
	
	@Autowired
	DossierStatusTypeDao dossierStatusTypeDao;
	
	
	@Autowired 
	ProcessStatusTypePropertyDao processStatusTypePropertyDao;
	
	@Autowired
	RoleDao roleDao;
	
	 @Autowired
	 UserDao userDao;
	/*
	@Test
	void createDossier() {
		Optional<Dossier> op  = dossierDao.findById(new Integer(10));
		Dossier dossierS = null;
		assertTrue(op.isPresent());
		if(!op.isPresent()) {
			Dossier dossier = new Dossier();
			dossier.setPersonId(new BigDecimal(1));
			dossier.setCreationDate(new Date(0));
			dossierS = dossierDao.save(dossier);
			assertNotNull(dossierS.getId());
		}else {
			dossierS = (Dossier) op.get();
			assertNotNull(dossierS.getId());
		}
		assertEquals(dossierS.getId(), 10);
		
	}
	@Test
	void createProcess() {
		Optional<Process> op  = processDao.findById(new Integer(13));
		Process processS = null;
		
		if(!op.isPresent()) {
			Process process = new Process();
			process.setName("MODONE");
			process.setPrefix("process.module.one.prefix");  
			
			processS = processDao.save(process);
			assertNotNull(processS.getId());
		}else {
			processS = (Process) op.get();
			assertNotNull(processS.getId());
		}
		assertTrue(op.isPresent());
		assertEquals(processS.getId(), 13);
		
	}
	@Test
	void createLang() {
		Optional<Language> op  = languageDao.findById(new Integer(15));
		Language ls = null;
		
		if(!op.isPresent()) {
			/*Language l = new Language();
			l.setName("FR");
			l.setLabel("label.french");  
			l.setIsoCode("FR");
			
			ls = languageDao.save(l);
			* /
			Language l = new Language();
			l.setName("EN");
			l.setLabel("label.english");  
			l.setIsoCode("EN");
			
			ls = languageDao.save(l);
			assertNotNull(ls.getId());
		}else {
			ls = (Language) op.get();
			assertNotNull(ls.getId());
		}
		//assertTrue(op.isPresent());
		assertEquals(ls.getId(), 15);
		
	}
	@Test
	void createDossierStatus() {
		Optional<Process> opss  = processDao.findById(new Integer(13)); 
		 
		Optional<Dossier> dop  = dossierDao.findById(new Integer(12));
		
		Optional<DossierStatusType> dst = dossierStatusTypeDao.findByName("MODONE_PROCESS_CREATED");
		
		DossierStatusType dossierStatusType;
		if(!dst.isPresent()) {
			dossierStatusType = new DossierStatusType();
			dossierStatusType.setName("MODONE_PROCESS_CREATED");
			dossierStatusType.setPrefix("dossier.modone.process.created");
			dossierStatusType.setPlacement("French");
			dossierStatusTypeDao.save(dossierStatusType);
		}else {
			dossierStatusType = dst.get();
		}
		
		DossierStatus  dossierStatus ;
		if(dop.isPresent()) {
			dossierStatus = new DossierStatus() ;
			dossierStatus.setDossier(dop.get());
			dossierStatus.setProcess(opss.get());
			dossierStatus.setResult(DossierResult.SUBMITTED);
			dossierStatus.setStatus(dossierStatusType);
			//done
			//dossierStatusDao.save(dossierStatus);
		}
	}
	 
	@Test
	void ProcessStatusTypeProperty() {
		Optional<ProcessStatusTypeProperty> pstp = processStatusTypePropertyDao.findByName("QUESTIONNAIRE_TEMPLATE_NAME");
		if(!pstp.isPresent()) {
			ProcessStatusTypeProperty processStatusTypeProperty = new ProcessStatusTypeProperty();
			processStatusTypeProperty.setName("QUESTIONNAIRE_TEMPLATE_NAME");
			Optional<Process> opss  = processDao.findById(new Integer(13)); 
			processStatusTypeProperty.setProcess(opss.get());
			processStatusTypeProperty.setValue("MODONE_FIRSTQ");
			Optional<DossierStatusType> dst = dossierStatusTypeDao.findByName("MODONE_PROCESS_CREATED");
			processStatusTypeProperty.setDossierStatusType(dst.get());
			processStatusTypePropertyDao.save(processStatusTypeProperty);
			assertTrue(true);
		}
	}
	*/
	@Test
	void RoleUser() {
		//REQUESTOR
		Role role= new Role();
		role.setName("REQUESTOR");
		role.setDescription("Anonyme user");
		
		List<Role> roles =new ArrayList<Role>();
	
		
		role = roleDao.save(role);
		
		roles.add(role);
		User user = new User();
		
		user.setUser_name("Nakous");
		user.setRoles(roles);
		user.setEmail("mustapha.nak@gmail.com");
		user.setLang("FR");
		user.setLabel("Developor");
		
		userDao.save(user);
	}
}
