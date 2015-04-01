package next.dao;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import next.model.Answer;
import next.model.Question;
import next.service.QnaDelete;

import org.junit.Before;
import org.junit.Test;

public class QnaDeleteTest {
	private Question question;
	private QnaDelete delete;
	private List<Answer> answers = null;
	private AnswerDao answerDao;
	@Before
	public void setup() {
		question = new Question(1L, "writer", "title", "content", new Date(), 0);
		delete = new QnaDelete();
		answers = new ArrayList<Answer>();
		answerDao = MockAnswerDao.getInstance();
		
	}
	
	@Test
	public void noAnswer() throws Exception {
		boolean result = delete.askDelete(question, answerDao);
		assertEquals(true, result);
	}
	@Test
	public void answerIsMe() throws Exception {
		answerDao.insert(new Answer(1L, "writer", "answered", new Date(), 1L));
		answerDao.insert(new Answer(1L, "writer", "answered", new Date(), 1L));
		question.setAnswers(answers);
		Question question1 = new Question(1L, "writer", "title", "contents", new Date(), 2);
		AnswerDao answerDao = MockAnswerDao.getInstance();
		boolean result = delete.askDelete(question1, answerDao);
		assertEquals(true, result);
	}
	//함수 두개 동시에 실행하면 에러가 난다. 눈눈난나. 
	@Test
	public void answerIsYou() throws Exception {
		answerDao.insert(new Answer(1L, "erin", "answered", new Date(), 1L));
		answerDao.insert(new Answer(1L, "writer", "answered", new Date(), 1L));
		Question question2 = new Question(1L, "writer", "title", "contents", new Date(), 2);
		boolean result = delete.askDelete(question2, answerDao);
		assertEquals(false, result);
	}

}
