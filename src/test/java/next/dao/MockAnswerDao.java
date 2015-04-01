package next.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import next.model.Answer;
import core.jdbc.JdbcTemplate;

public class MockAnswerDao implements AnswerDao{
	private List<Answer> answers = new ArrayList<Answer>();
	private static AnswerDao answerDao = new MockAnswerDao();

	//12번도 완성되어 있었다고 한다. 
	public static AnswerDao getInstance(){
		return answerDao;
	}
	public void insert(Answer answer) {
		answers.add(answer);
	}

	public List<Answer> findAllByQuestionId(long questionId) {
		List<Answer> result = new ArrayList<Answer>();
		for (Answer answer : answers) {
			if(answer.getQuestionId() == questionId){
				result.add(answer);
			}
		}
		return result;
		
	}
	
	public void delete(Answer answer){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
		String sql = "DELETE FROM ANSWERS WHERE questionId=? AND answerId=?;";
		jdbcTemplate.update(sql, answer.getQuestionId(), answer.getAnswerId());
	}
}
