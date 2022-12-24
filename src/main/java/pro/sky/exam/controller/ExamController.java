package pro.sky.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.exam.exception.NotEnoughQuestionsException;
import pro.sky.exam.model.Question;
import pro.sky.exam.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {
    private final ExaminerService examinerService;

    @ExceptionHandler(NotEnoughQuestionsException.class)
    public ResponseEntity<String> handleException() {
        return ResponseEntity.badRequest().body("Недостаточно вопросов для выборки");
    }

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
