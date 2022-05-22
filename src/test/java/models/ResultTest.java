package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

public class ResultTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }
    @Test
    public void testSuccess() {
        Result result = new Result.Success(10);

        Consumer<Integer> success = System.out::println;
        Consumer<String> failure = v -> System.out.println("Err:"+v);

        result.bind(success, failure);
        Assertions.assertEquals("10", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testFailure() {
        Result result = new Result.Failure("Err");

        Consumer<Integer> success = System.out::println;
        Consumer<String> failure = System.out::println;

        result.bind(success, failure);
        Assertions.assertEquals("Err", outputStreamCaptor.toString().trim());
    }
}
