package models;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Result<T> {


    void bind(Consumer<T> success, Consumer<String> failure);
    <U> Result<U> map(Function<T, U> mapper);
    <U> Result<U> flatMap(Function<T, Result<U>> mapper);

    static <T> Result<T> of(T value) {
        return value != null
                ? success(value)
                : failure("Null value");
    }
    static <T> Result<T> of(T value, String message) {
        return value != null
                ? success(value)
                : failure(message);
    }

    static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }

    static <T> Result<T> failure(Exception e) { return new Failure<>(e.getMessage()); }

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    class Success<T> implements Result<T> {
        private final T value;
        public Success(T t){
            value = t;
        }

        @Override
        public void bind(Consumer<T> success, Consumer<String> failure){
            success.accept(value);
        }

        @Override
        public <U> Result<U> map(Function<T, U> mapper) {
            try {
                return success(mapper.apply(value));
            } catch (Exception e) {
                return failure(e.getMessage());
            }
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
            try {
                return mapper.apply(value);
            } catch (Exception e) {
                return failure(e.getMessage());
            }
        }
    }

    class Failure<T> implements Result<T> {
        private final String errorMessage;

        public Failure(String s) {
            this.errorMessage = s;
        }

        @Override
        public void bind(Consumer<T> success, Consumer<String> failure){
            failure.accept(errorMessage);
        }

        @Override
        public <U> Result<U> map(Function<T, U> mapper) {
            return failure(errorMessage);
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
            return failure(errorMessage);
        }

    }
}
