package it.unibo.tw.db;

import it.unibo.tw.model.Student;

import java.util.Calendar;

public class TestStudentRepository {
    
    public static void main(String[] args) throws PersistenceException {
        
        StudentRepository sp = new StudentRepository(DataSource.DB2);
    	sp.dropAndCreateTable();
        
        Student s = new Student();
        Calendar c = Calendar.getInstance();
        c.set(1984, 1, 24);
        s.setCode(3);
        s.setFirstName("Luisa");
        s.setLastName("Verdi");
        s.setBirthDate(c.getTime());
        sp.persist(s);

        s = new Student();
        c.set(1985, 4, 2);    
        s.setCode(4);
        s.setFirstName("Anna");
        s.setLastName("Bruni");
        java.util.Date d = c.getTime(); 
        s.setBirthDate(d);
        sp.persist(s);
    
        for (Student sc : sp.findStudentsByBirthDate(d)) {
            System.out.println(sc.getFirstName()+" "+ sc.getLastName()+" "+sc.getBirthDate());
        } 
            
    }
    
}
