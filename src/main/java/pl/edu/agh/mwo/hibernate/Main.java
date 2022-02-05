package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		
		User user1 = new User();
		main.aaa(user1);
		
		main.close();
	}

private void aaa(User user) {
	Transaction transaction = session.beginTransaction();
	session.save(user);
	transaction.commit();
}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
}
