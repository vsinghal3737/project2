package edu.asu.ser516.blackBoard.quiz.dao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import edu.asu.ser516.blackBoard.quiz.bean.Answer;
import edu.asu.ser516.blackBoard.quiz.bean.HibernateUtil;
import edu.asu.ser516.blackBoard.quiz.bean.Question;
import edu.asu.ser516.blackBoard.quiz.bean.Quiz;

public class QuestionDAO {
	public void addQuestion(Question ques) {
		Transaction transaction = null;
		try  {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(ques);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public List<Question> getQuestionsByQuizId(int quizId){
		Transaction transaction = null;
		List<Question> quesList = new ArrayList<Question>();

		try  {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> query = builder.createQuery(Question.class);
			Root<Question> root = query.from(Question.class);
			Join<Question,Quiz> join = root.join("quiz");
			query.select(root).where(builder.equal(join.get("quizId"),quizId));
			Query<Question> q = session.createQuery(query);
			quesList = q.getResultList();
			for(Question qu: quesList)
				System.out.println(qu.toString());
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return quesList;
		}
		return quesList;
	}

	public int getPointByQuestion(String ques) {
		Transaction transaction = null;
		int points = -1;
		try  {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
			Root<Question> root = query.from(Question.class);
			query.select(root.<Integer>get("points")).where(root.get("question").in(ques));
			Query<Integer> q=session.createQuery(query);
			points=q.getSingleResult();
	        transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return points;
		}
		return points;
	}
	
	
	public void deleteQuestionByQuestionId(String quesId){
		Transaction transaction = null;
		Question quesList = null;
		try  {
			int qId = Integer.parseInt(quesId);
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			quesList = (Question) session.get(Question.class, qId);
//			System.out.println(quesList.toString());
			session.delete(quesList);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return ;
		}
		return ;
	}
	public List<Answer> getQuestionsAndAnswers(int quizId) {
		// TODO Auto-generated method stub
	
		Transaction transaction = null;
	       List<Answer> quesList = new ArrayList<Answer>();
	       try  {
	           Session session = HibernateUtil.getSessionFactory().openSession();
	           transaction = session.beginTransaction();
	           CriteriaBuilder builder = session.getCriteriaBuilder();
	           CriteriaQuery<Answer> query = builder.createQuery(Answer.class);
	           Root<Answer> root = query.from(Answer.class);
	           Join<Answer,Question> join = root.join("question");
	           query.select(root).where(root.get("quiz").in(quizId));
	           Query<Answer> q=session.createQuery(query);
	           quesList= q.getResultList();
	           for (Answer name : quesList) {
	               System.out.println(name);
	           }
	           transaction.commit();
	       } catch (HibernateException e) {
	           e.printStackTrace();
	           if (transaction != null) {
	               transaction.rollback();
	           }
	       }
	       return quesList;

		
		
	}
	
}
