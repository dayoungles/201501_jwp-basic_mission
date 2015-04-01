package next.dao;

import java.util.List;

import next.model.Answer;

public interface AnswerDao {

	public abstract void insert(Answer answer);

	public abstract List<Answer> findAllByQuestionId(long questionId);

	public abstract void delete(Answer answer);

}