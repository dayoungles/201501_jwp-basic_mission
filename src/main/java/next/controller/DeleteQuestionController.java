package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.DispatcherServlet;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteQuestionController extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		logger.debug("questionID", questionId);
		
		//questionId로 question을 찾아온다.
		QuestionDao dao = QuestionDao.getInstance();
		Question question = dao.findById(questionId);
		boolean othersReply = false;//다른 놈 답변이 있으면 true
		if(question.getCountOfComment() != 0){
			AnswerDao answerDao = AnswerDao.getInstance();
			List<Answer> answerList= answerDao.findAllByQuestionId(questionId);
			for (Answer answer : answerList) {
				if(!answer.getWriter().equals(question.getWriter())){
					logger.info("다른 사용자 댓글이 있어서 삭제 불가 ");
					othersReply = true;
					break;
				}
			}
		}

		if(!othersReply){
			dao.deleteQuestion(questionId);
		}
		
		List<Question> questions = dao.findAll();
		ModelAndView mav = jstlView("list.jsp");
		mav.addObject("questions", questions);
		return mav;
	}

	
}
