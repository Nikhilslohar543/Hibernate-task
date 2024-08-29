package Problems;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.RowCountProjection;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import Entity.Department;
import Entity.Employee;

public class Question_3 {
	
	//Q.3 fetch all employee entites order them by their last name in asc order

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(Employee.class);
		cfg.addAnnotatedClass(Department.class);
		
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		Transaction t = null;
		
		try {
		t = session.beginTransaction();
		
		Criteria c = session.createCriteria(Employee.class);
		
		c.addOrder(Order.asc("lastname"));
								
		List<Employee> list = c.list();
		
		for(Employee emp : list) {
			System.out.println("employees last name :"+emp.getLastname());
		}
		
		t.commit();
		
		}catch(Exception e) {
			if(t!=null)
				t.rollback();
			e.printStackTrace();
			
		}finally {
			session.close();
			factory.close();
			
		}
		
		
		 
	}

	private static Criterion COUNT(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
