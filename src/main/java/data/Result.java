package data;

import java.util.function.Consumer;

public interface Result<T> {
    void bind(Consumer<T> success, Consumer<String> failure);

    static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }

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
    }
}
