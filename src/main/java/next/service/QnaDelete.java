package next.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.AddReplyController;
import next.dao.AnswerDao;
import next.dao.JdbcAnswerDao;
import next.model.Answer;
import next.model.Question;

public class QnaDelete {
	private static final Logger logger = LoggerFactory.getLogger(AddReplyController.class);


	private boolean isOtherReplyExist(Question question,AnswerDao answerDao) {
		
		boolean isOthersReplyExist = false;//다른 놈 답변이 있으면 true
		if(question.getCountOfComment() != 0){
			List<Answer> answerList= answerDao.findAllByQuestionId(question.getQuestionId());
			for (Answer answer : answerList) {
				logger.debug("answer.getWriter: ", answer.getWriter());
				logger.debug("question.getWriter: ", question.getWriter());
				if(!answer.getWriter().equals(question.getWriter())){
					logger.info("다른 사용자 댓글이 있어서 삭제 불가 ");
					isOthersReplyExist = true;
					break;
				}
			}
		}
		return isOthersReplyExist;
	}


	public boolean askDelete(Question question, AnswerDao answerDao) {
		if (isOtherReplyExist(question, answerDao)) {
			return false;
		}
		return true;
	}

}
