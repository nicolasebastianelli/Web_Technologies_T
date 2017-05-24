package it.unibo.tw.hibernate;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class HibernateTest {

	public static void main(String[] args){// throws Exception{

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = null;

		try {		  
			tx = session.beginTransaction();	

			// Student

			Student student = new Student();
			student.setId(1);
			student.setFirstName("Marco");
			student.setLastName("Rossi");
			Calendar c = Calendar.getInstance();
			c.set(1984, 1, 24);
			student.setBirthDate(c.getTime());
			session.persist(student);

			student = new Student();
			student.setId(2);
			student.setFirstName("Giovanni");
			student.setLastName("Gialli");
			c = Calendar.getInstance();
			c.set(1983, 4, 13);
			student.setBirthDate(c.getTime());
			session.persist(student);	

			// Courses

			Course course = new Course();
			course.setId(1);
			course.setName("Tecnologie Web");
			session.persist(course);

			course = new Course();
			course.setId(2);
			course.setName("Fondamenti di Informatica T1");
			session.persist(course);

			// SCMapping
			SCMapping mapping = new SCMapping();
			mapping.setIdCourse(1);
			mapping.setIdStudent(1);
			session.persist(mapping);

			mapping = new SCMapping();
			mapping.setIdCourse(2);
			mapping.setIdStudent(1);
			session.persist(mapping);

			mapping = new SCMapping();
			mapping.setIdCourse(1);
			mapping.setIdStudent(2);
			session.persist(mapping);




			// richiedo l'elenco degli studenti che hanno cognome "Gialli"
			// versione SQL
			System.out.println();
			System.out.println("query students: sql/hql");

			// query HQL: il nome della tabella  ottenuto tramite il mapping  
			//			presente nel file XML relativo alla classe Student
			Query query = session.createQuery("from "+Student.class.getSimpleName()+" where lastName = ?");

			// variante SQL: classica query in cui sis pecifica il nome della tabella
			//Query query = session.createSQLQuery("select * from students where lastName = ?").addEntity(Student.class);

			query.setString(0, "Gialli");
			List<Student> students = query.list();
			System.out.println("students.size() "+ students.size());
			Iterator<Student> it = students.iterator();
			while(it.hasNext()){
				Student studentResult = it.next();
				System.out.println(studentResult.getFirstName()+" "+ studentResult.getLastName()+" "+studentResult.getBirthDate());
			}

			// versione Criteria: soluzione completamente object-oriented
			System.out.println();
			System.out.println("query students: criteria");
			Criteria criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("lastName", "Gialli"));
			students = criteria.list();
			System.out.println("students.size() "+ students.size());
			for(Student studentResult : students){
				System.out.println(studentResult.getFirstName()+" "+ studentResult.getLastName()+" "+studentResult.getBirthDate());
			}


			Query query1 = session.createQuery("from "+Student.class.getSimpleName()+" where id = ?");
			Query query2 = session.createQuery("from "+Course.class.getSimpleName()+" where id = ?");
			Query query3 = session.createQuery("from "+SCMapping.class.getSimpleName()+" where idStudent = ?");
			query1.setInteger(0, 1);
			student = (Student) query1.list().get(0);
			System.out.println(student.getFirstName()+" "+student.getLastName()+" frequenta i seguenti corsi:");
			query3.setInteger(0,student.getId());
			for(Object m : query3.list()){
				query2.setInteger(0, ((SCMapping) m).getIdCourse());
				course = (Course)query2.list().get(0);
				System.out.println(course.getName());
			}
			System.out.println();
			
			query3 = session.createQuery("from "+SCMapping.class.getSimpleName()+" where idCourse = ?");
			query2.setInteger(0, 1);
			course = (Course) query2.list().get(0);
			System.out.println(course.getName()+" frequentato dagli studenti:");
			query3.setInteger(0,course.getId());
			for(Object m : query3.list()){
				query1.setInteger(0, ((SCMapping) m).getIdStudent());
				student = (Student) query1.list().get(0);
				System.out.println(student.getFirstName()+" "+student.getLastName());
			}
			System.out.println();
			System.out.println();
			tx.commit();
		}
		catch (Exception e1) {
			if (tx != null){
				try{
					tx.rollback();
				}
				catch(Exception e2){
					e2.printStackTrace();
				}
			}
			e1.printStackTrace();
		}
		finally {
			session.close();
		}	

	}
}
