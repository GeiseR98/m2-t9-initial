package ru.yandex.practicum.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cats")
public class CatsInteractionController {

    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        happiness++;
        return Map.of("talk", "Мяу");
    }

    @GetMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
        happiness += count;
        return Map.of("talk", "Муррр. ".repeat(count));
    }

    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

    // перечисление обрабатываемых исключений
    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
// в аргументах указывается родительское исключение
    public Map<String, String> handleIncorrectCount(final RuntimeException e) {
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }
    @ExceptionHandler
    public Map<String, String> handleError(final RuntimeException e) {
        return Map.of("error", "Произошла ошибка!",
                "errorMessage", e.getMessage()
        );
    }
}