package pro.sky.exam.service;

import org.springframework.stereotype.Service;
import pro.sky.exam.exception.QuestionSetIsEmptyException;
import pro.sky.exam.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{
    private final static Random RANDOM = new Random();
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question questionAnswer = new Question(question, answer);
        questions.add(questionAnswer);
        return questionAnswer;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new QuestionSetIsEmptyException();
        }
        return questions.stream().skip(RANDOM.nextInt(questions.size())).findFirst().orElseThrow();
    }
}
