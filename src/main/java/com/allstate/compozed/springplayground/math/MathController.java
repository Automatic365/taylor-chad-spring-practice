package com.allstate.compozed.springplayground.math;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.IntStream;

import static org.springframework.expression.common.ExpressionUtils.toInt;

@RestController
final class MathController {

    @RequestMapping(path = "/math/square/{number}")
    Map<String, Integer> square(@PathVariable int number) {
        return Collections.singletonMap("square", number * number);
    }

    @RequestMapping(path = "/math/factorial/{number}")
    Map<String, Integer> factorial(@PathVariable final int number) {

        Integer fact = IntStream
                        .rangeClosed(1, number)
                        .reduce(1, (acc, val) -> acc * val);

        return Collections.singletonMap("factorial", fact);
    }
}
