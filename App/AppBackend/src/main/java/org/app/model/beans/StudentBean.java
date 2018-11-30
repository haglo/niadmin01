package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.audit.RevInfo;
import org.app.model.dao.StudentDAO;
import org.app.model.entity.Student;
import org.app.model.entity.Student_AUD;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

@Stateless
@Remote(StudentDAO.class)
public class StudentBean implements StudentDAO {

	@PersistenceContext
	private EntityManager em;


	@Override
	public Student create(Student student) {
		em.persist(student);
		em.flush();
		return student;
	}

	@Override
	public Student update(Student student) {
		student = em.merge(student);
		em.flush();
		return student;
	}

	@Override
	public void remove(Integer id) {
		Student toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
		em.flush();
	}

	@Override
	public Student findByID(Integer id) {
		return em.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		return em.createNamedQuery(Student.QUERY_FIND_ALL, Student.class).getResultList();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Student_AUD> findAudById(Integer id) {
		List<Student_AUD> listAuditedStudents = new ArrayList<Student_AUD>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Student.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();

		for (Object[] revData : revDatas) {
			listAuditedStudents
					.add(new Student_AUD((Student) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return listAuditedStudents;

	}
	
}
