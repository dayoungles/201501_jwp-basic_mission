package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.JdbcAnswerDao;
import next.dao.JdbcQuestionDao;
import next.dao.QuestionDao;
import next.model.Answer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteReplyController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AddReplyController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AnswerDao answerDao = JdbcAnswerDao.getInstance();
		QuestionDao questionDao = JdbcQuestionDao.getInstance();
		
		long answerId = ServletRequestUtils.getLongParameter(request, "answerId");
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		Answer answer = new Answer(answerId, questionId);
		logger.debug("answer={}", answer);
		answerDao.delete(answer);
		questionDao.minusOneCountOfComment(questionId);
		
		ModelAndView mav = jsonView();
		
		return mav;
	}

}
