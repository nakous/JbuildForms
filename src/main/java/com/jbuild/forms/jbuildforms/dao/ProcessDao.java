package com.jbuild.forms.jbuildforms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.Process;

@Repository
public interface ProcessDao extends JpaRepository<Process, Integer>{

	Process  findByName(String name);
}
