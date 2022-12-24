package pro.sky.exam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.exam.exception.NotEnoughQuestionsException;
import pro.sky.exam.model.Question;
import pro.sky.exam.service.ExaminerServiceImpl;
import pro.sky.exam.service.QuestionService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTests {
    @Mock
    QuestionService questionService;
    @InjectMocks
    ExaminerServiceImpl examinerService;

    @Test
    void whenAmountOfQuestionsEnoughThenGetQuestions() {
        List<Question> questionList = List.of(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3")
        );
        when(questionService.getAll()).thenReturn(questionList);
        when(questionService.getRandomQuestion()).thenReturn(questionList.get(0), questionList.get(1));
        assertThat(examinerService.getQuestions(2))
                .hasSize(2)
                .containsOnly(questionList.get(0), questionList.get(1));
    }

    @Test
    void whenAmountOfQuestionsNotEnoughThenGetQuestionsException() {
        when(questionService.getAll()).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> examinerService.getQuestions(100))
                .isInstanceOf(NotEnoughQuestionsException.class);
    }
}
