package Problems;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Entity.Department;
import Entity.Employee;
import Entity.Project;

public class Question_1 {
	
	//Q.1 reterive all employees 

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(Department.class);
		cfg.addAnnotatedClass(Employee.class);
		cfg.addAnnotatedClass(Project.class);
		
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		Transaction t = null;
		try {
		t = session.beginTransaction();
		
		Criteria c = session.createCriteria(Department.class);
		
		List<Department> list = c.list();
		
		List<Employee> data = new ArrayList<Employee>();
 
		for(Department o : list) {
			Set<Employee> list1 = o.getEmployees();
			
			for(Employee o1 : list1) {
				
				data.add(o1);
			}
		}
		
		for(Employee e : data) {
			Long id = e.getId();
			String email = e.getEmail();
			String name = e.getFirstname();
			String lname = e.getLastname();
			int salary = e.getSalary();
			Long dep_id = e.getDepartment().getDept_id();
			System.out.println(id+" "+email+" "+name +" "+lname+" "+ salary+" "+dep_id);
		}

		t.commit();

		}catch(Exception e) {
			if(t!=null)
				t.rollback();
			e.printStackTrace();
			
		}finally {
		session.close();
		factory.close();}
	}

}


