package pro.sky.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.exam.exception.NotEnoughQuestionsException;
import pro.sky.exam.exception.QuestionSetIsEmptyException;
import pro.sky.exam.model.Question;
import pro.sky.exam.service.JavaQuestionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JavaQuestionServiceTests {
    private JavaQuestionService javaQuestionService;

    private final List<Question> questions = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4"),
            new Question("q5", "a5")
    );

    @BeforeEach
    void setUp() {
        this.javaQuestionService = new JavaQuestionService();
        questions.forEach(javaQuestionService::add);
    }

    @Test
    void addQuestionAnswer() {
        Question expected = this.javaQuestionService.add("q6", "a6");
        assertThat(this.javaQuestionService.getAll()).hasSize(6).contains(expected);
    }

    @Test
    void addQuestion() {
        Question expected = new Question("q7", "a7");
        Question actual = this.javaQuestionService.add(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(this.javaQuestionService.getAll()).hasSize(6).contains(expected);
    }

    @Test
    void removeQuestion() {
        Question expected = this.questions.get(3);
        Question actual = this.javaQuestionService.remove(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(this.javaQuestionService.getAll()).hasSize(4).doesNotContain(expected);
    }

    @Test
    void getRandomQuestion() {
        Question actual = this.javaQuestionService.getRandomQuestion();
        assertThat(actual).isIn(questions);
    }

    @Test
    void getAllQuestion() {
        assertThat(this.javaQuestionService.getAll()).hasSize(5).containsAll(this.questions);
    }

    @Test
    void whenListIsEmptyThenGetRandomQuestionReturnsException() {
        this.javaQuestionService = new JavaQuestionService();
        assertThatThrownBy(() -> javaQuestionService.getRandomQuestion())
                .isInstanceOf(QuestionSetIsEmptyException.class);
    }
}
