package next.dao;

import java.util.List;

import next.model.Question;

public interface QuestionDao {

	public abstract void insert(Question question);

	public abstract List<Question> findAll();

	public abstract Question findById(long questionId);

	public abstract void plusOneMoreCommentCount(long questionId);

	public abstract void minusOneCountOfComment(long questionId);

	public abstract void deleteQuestion(long questionId);

}