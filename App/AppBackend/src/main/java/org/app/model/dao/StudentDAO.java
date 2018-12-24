package org.app.model.dao;

import java.util.List;

import org.app.model.entity.Student;
import org.app.model.entity.Student_AUD;


public interface StudentDAO {

	public Student create(Student xentity);

	public Student update(Student xentity);

	public void remove(Integer id);
	
	public List<Student> findAll();

	public Student findByID(Integer id);
	
	public List<Student_AUD> findAudById(Integer id);

}