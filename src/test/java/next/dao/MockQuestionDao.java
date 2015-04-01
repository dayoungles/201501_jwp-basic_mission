package next.dao;

import java.util.List;

import next.model.Question;

public class MockQuestionDao implements QuestionDao {

	public void insert(Question question){

	};
	public List<Question> findAll(){
		return null;
	};

	public Question findById(long questionId){
		return null;
	};

	public void plusOneMoreCommentCount(long questionId){
		
	};

	public void minusOneCountOfComment(long questionId){
		
	};

	public void deleteQuestion(long questionId){
		
	};

}