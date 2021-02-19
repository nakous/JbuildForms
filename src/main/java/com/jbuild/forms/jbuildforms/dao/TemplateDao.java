package com.jbuild.forms.jbuildforms.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.enumeration.TemplateName;
import com.jbuild.forms.jbuildforms.enumeration.TemplateType;
import com.jbuild.forms.jbuildforms.model.Template;

@Repository
public interface TemplateDao extends CrudRepository<Template, Integer>{
	
	//@Query("SELECT TEMP FROM Template AS TEMP where TEMP.NAME =:name and TEMP.TYPE= :type ")
	//Template findByNameAndType(@Param("name") String Name ,@Param("type") TemplateType Type);
	
	Template findByName(TemplateName name);
	
	 
}
