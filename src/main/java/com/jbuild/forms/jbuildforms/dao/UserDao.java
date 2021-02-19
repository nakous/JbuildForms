package com.jbuild.forms.jbuildforms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jbuild.forms.jbuildforms.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	 
	@Query("SELECT u FROM User AS u where user_id =:id ")
	User findByUserId(@Param("id") Long user_id);

}
