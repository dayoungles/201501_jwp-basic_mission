package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Answer;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class JdbcAnswerDao implements AnswerDao {
	private static JdbcAnswerDao answerDao = new JdbcAnswerDao();
	
	private JdbcAnswerDao(){
		
	}
	//12번도 완성되어 있었다고 한다. 
	public static JdbcAnswerDao getInstance(){
		return answerDao;
	}
	/* (non-Javadoc)
	 * @see next.dao.MockAnswerDao#insert(next.model.Answer)
	 */
	@Override
	public void insert(Answer answer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, answer.getWriter(),
				answer.getContents(),
				new Timestamp(answer.getTimeFromCreateDate()),
				answer.getQuestionId());
	}

	/* (non-Javadoc)
	 * @see next.dao.MockAnswerDao#findAllByQuestionId(long)
	 */
	@Override
	public List<Answer> findAllByQuestionId(long questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
				+ "order by answerId desc";
		
		RowMapper<Answer> rm = new RowMapper<Answer>() {
			@Override
			public Answer mapRow(ResultSet rs) throws SQLException {
				return new Answer(
						rs.getLong("answerId"),
						rs.getString("writer"), 
						rs.getString("contents"),
						rs.getTimestamp("createdDate"), 
						questionId);
			}
		};
		
		return jdbcTemplate.query(sql, rm, questionId);
	}
	
	/* (non-Javadoc)
	 * @see next.dao.MockAnswerDao#delete(next.model.Answer)
	 */
	@Override
	public void delete(Answer answer){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
		String sql = "DELETE FROM ANSWERS WHERE questionId=? AND answerId=?;";
		jdbcTemplate.update(sql, answer.getQuestionId(), answer.getAnswerId());
	}
}
