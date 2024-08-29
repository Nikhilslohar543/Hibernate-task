package Problems;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import Entity.Department;
import Entity.Employee;

public class Question_5 {

	// Q.5 fetching firstname and lastname of employees

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(Department.class);
		cfg.addAnnotatedClass(Employee.class);

		SessionFactory factory = cfg.buildSessionFactory();

		Session session = factory.openSession();

		Transaction t = null;

		try {

			t = session.beginTransaction();

			Criteria c = session.createCriteria(Employee.class);

			Projection p = Projections.property("firstname");
			Projection p1 = Projections.property("lastname");

			ProjectionList list = Projections.projectionList();
			list.add(p);
			list.add(p1);

			c.setProjection(list);

			List<Object[]> emp = c.list();

//			String hql = "select firstname , lastname from Employee";
//
//			List<Object[]> emp = session.createQuery(hql).list();

			for (Object[] o : emp) {
				String first = (String) o[0];
				String last = (String) o[1];
				System.out.println("first name :" + first + ", last name :" + last);
			}

			t.commit();

		} catch (Exception e) {

			if (e != null)
				t.rollback();
			e.printStackTrace();

		} finally {
			factory.close();
			session.close();
		}

	}
}
