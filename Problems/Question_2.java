package Problems;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import Entity.Department;
import Entity.Employee;

public class Question_2 {
	
	//Q.2 finding employee whose salary is greater than 20000

	public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Department.class);
        cfg.addAnnotatedClass(Employee.class);

        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Criteria c = session.createCriteria(Employee.class);
            c.add(Restrictions.gt("salary", 20000));

            List<Employee> list = c.list();

            for (Employee e : list) {
                System.out.println(e);
            }
            
            tx.commit();
        
        } catch (Exception e) {
            if (tx != null) 
            	tx.rollback();
            e.printStackTrace();
            
        } finally {
            session.close();
            factory.close();
        }
    }

}
