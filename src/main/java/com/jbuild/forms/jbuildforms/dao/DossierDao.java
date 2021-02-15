package com.jbuild.forms.jbuildforms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.Dossier;

@Repository
public interface DossierDao extends JpaRepository<Dossier, Integer> {

}
