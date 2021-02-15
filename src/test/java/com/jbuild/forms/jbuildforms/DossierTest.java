package com.jbuild.forms.jbuildforms;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.jbuild.forms.jbuildforms.dao.ProcessDao;
import com.jbuild.forms.jbuildforms.model.Dossier;
import com.jbuild.forms.jbuildforms.model.DossierStatus;
import com.jbuild.forms.jbuildforms.model.Process;
class DossierTest {
	
	@Resource
	ProcessDao processDao;
	
	@Test
	void test() {
		//fail("Not yet implemented");
		Process  process= new Process();
		
		process.setName("MODULE");
		process.setPrefix("process.module");
		Process  process2= processDao.save(process);
		//Student student2 = studentRepository.findOne(1);
        assertEquals("MODULE", process2.getName());
		
		/*Dossier dossier = new Dossier();
		dossier.setPersonId(new BigDecimal(10));
		Set<DossierStatus> statuses = new HashSet<DossierStatus>();
		DossierStatus status = new DossierStatus();
		status.setProcess(process);
		dossier.setStatuses(statuses);*/
	}

}
