package next.controller;

import java.util.List;

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

public class AddReplyController extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(AddReplyController.class);
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Answer> answers;
		Answer answer;
		AnswerDao answerDao = JdbcAnswerDao.getInstance();
		QuestionDao questionDao = JdbcQuestionDao.getInstance();
		
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String content = ServletRequestUtils.getStringParameter(request, "contents");
		
		answer = new Answer(writer, content, questionId);
		logger.debug("answer created {}", answer);
		answerDao.insert(answer);
		questionDao.plusOneMoreCommentCount(questionId);
		ModelAndView mav = jsonView();
		return mav;
	}

}
