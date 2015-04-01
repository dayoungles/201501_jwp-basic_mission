package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class JdbcQuestionDao implements QuestionDao {
	private static QuestionDao questionDao = new JdbcQuestionDao();
	private JdbcQuestionDao(){
	}
	public static QuestionDao getInstance(){
		return questionDao;
	}
	/* (non-Javadoc)
	 * @see next.dao.QuestionDaoo#insert(next.model.Question)
	 */
	@Override
	public void insert(Question question) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfComment) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, 
				question.getWriter(), 
				question.getTitle(), 
				question.getContents(),
				new Timestamp(question.getTimeFromCreateDate()), 
				question.getCountOfComment());
	}
	
	/* (non-Javadoc)
	 * @see next.dao.QuestionDaoo#findAll()
	 */
	@Override
	public List<Question> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, createdDate, countOfComment FROM QUESTIONS "
				+ "order by questionId desc";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
			
		};
		
		return jdbcTemplate.query(sql, rm);
	}

	/* (non-Javadoc)
	 * @see next.dao.QuestionDaoo#findById(long)
	 */
	@Override
	public Question findById(long questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfComment FROM QUESTIONS "
				+ "WHERE questionId = ?";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
			
		};
		
		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}
	
	/* (non-Javadoc)
	 * @see next.dao.QuestionDaoo#plusOneMoreCommentCount(long)
	 */
	@Override
	public void plusOneMoreCommentCount(long questionId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "UPDATE QUESTIONS SET countOfComment=countOfComment+1 WHERE questionId=?;";
		
		jdbcTemplate.update(sql, questionId);	
	}

	/* (non-Javadoc)
	 * @see next.dao.QuestionDaoo#minusOneCountOfComment(long)
	 */
	@Override
	public void minusOneCountOfComment(long questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "UPDATE QUESTIONS SET countOfComment=countOfComment-1 WHERE questionId=?;";
		jdbcTemplate.update(sql, questionId);
	}
	
	/* (non-Javadoc)
	 * @see next.dao.QuestionDaoo#deleteQuestion(long)
	 */
	@Override
	public void deleteQuestion(long questionId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "DELETE FROM QUESTIONS WHERE questionId=?";
		jdbcTemplate.update(sql, questionId);
	}
}
