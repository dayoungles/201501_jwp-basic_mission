package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class SaveController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Question question;
		List<Question> questions;
		QuestionDao questionDao = QuestionDao.getInstance();
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		question = new Question(writer, title, contents);
		questionDao.insert(question);
		questions = questionDao.findAll();
		ModelAndView mav = jstlView("redirect:/list.next");
		mav.addObject("questions", questions);
		return mav;
	}

}
