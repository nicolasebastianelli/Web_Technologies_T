package it.unibo.tw.dao;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class DAOTest {
	
	public static final int DAO = DAOFactory.DB2;
	
	public static void main(String[] args) {
		
		// Student
		
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		StudentDAO studentDAO = daoFactoryInstance.getStudentDAO();
		studentDAO.dropTable();
		studentDAO.createTable();
		
		StudentDTO s = new StudentDTO();
		Calendar c = Calendar.getInstance();
		c.set(1984, 1, 24);
		s.setId(1);
		s.setFirstName("Luisa");
		s.setLastName("Verdi");
		s.setBirthDate(c.getTime());
		studentDAO.create(s);

		s = new StudentDTO();
		c.set(1985, 4, 2);
		s.setId(2);
		s.setFirstName("Anna");
		s.setLastName("Bruni");
		java.util.Date d = c.getTime(); 
		s.setBirthDate(d);
		studentDAO.create(s);
		
		for (StudentDTO sc : studentDAO.findStudentByLastName("Verdi")) {
			System.out.println(sc.getFirstName()+" "+ sc.getLastName()+" "+sc.getBirthDate());
		}
		System.out.println();
		
		
		// Courses
		
		CourseDAO courseDAO = DAOFactory.getDAOFactory(DAO).getCourseDAO();
		courseDAO.dropTable();
		courseDAO.createTable();
		
		CourseDTO course = new CourseDTO();
		course.setId(1);
		course.setName("Tecnologie Web");
		courseDAO.create(course);

		course = new CourseDTO();
		course.setId(2);
		course.setName("Fondamenti di Informatica T1");
		courseDAO.create(course);
		
		course = courseDAO.read(1);
		System.out.println(course.getName());
		System.out.println();
				
		
		// StudentCoursesMapping
		
		CourseStudentMappingDAO mappingDAO = DAOFactory.getDAOFactory(DAO).getStudentCourseMappingDAO();
		mappingDAO.dropTable();
		mappingDAO.createTable();
		
		CourseStudentMappingDTO mapping = new CourseStudentMappingDTO();
		mapping.setIdCourse(1);
		mapping.setIdStudent(1);
		mappingDAO.create(mapping);
		
		mapping = new CourseStudentMappingDTO();
		mapping.setIdCourse(2);
		mapping.setIdStudent(1);
		mappingDAO.create(mapping);
		
		mapping = new CourseStudentMappingDTO();
		mapping.setIdCourse(1);
		mapping.setIdStudent(2);
		mappingDAO.create(mapping);
		
		StudentDTO student = studentDAO.read(1);
		System.out.println(student.getFirstName()+" "+student.getLastName()+" frequenta i seguenti corsi:");
		for(CourseStudentMappingDTO m : mappingDAO.findCoursesByStudentId(student.getId())){
			course = courseDAO.read(m.getIdCourse());
			System.out.println(course.getName());
		}
		System.out.println();
		
		student = studentDAO.read(2);
		System.out.println(student.getFirstName()+" "+student.getLastName()+" frequenta i seguenti corsi:");
		for(CourseStudentMappingDTO m : mappingDAO.findCoursesByStudentId(student.getId())){
			course = courseDAO.read(m.getIdCourse());
			System.out.println(course.getName());
		}
		System.out.println();
		System.out.println();
		
		File f = new File("Result.txt");
        try {
        if(!f.exists())
        {
				f.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(f);
        
        PrintWriter out = new PrintWriter(fos);
        out.println();      
        out.close();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
