package models;

import java.util.function.Supplier;

public abstract class TailCall<T> {
    public abstract TailCall<T> resume();
    public abstract T eval();
    public abstract boolean isSuspend();

    public static class Return<T> extends TailCall<T> {
        private final T t;

        public Return(T t){
            this.t = t;
        }

        @Override
        public TailCall<T> resume() {
            throw new IllegalStateException("Return has no resume");
        }

        @Override
        public T eval() {
            return t;
        }

        @Override
        public boolean isSuspend() {
            return false;
        }
    }

    public static class Suspend<T> extends TailCall<T> {
        private final Supplier<TailCall<T>> resume;

        public Suspend(Supplier<TailCall<T>> resume){
            this.resume = resume;
        }

        @Override
        public TailCall<T> resume() {
            return resume.get();
        }

        @Override
        public T eval() {
            throw new IllegalStateException("Return has no resume");
        }

        @Override
        public boolean isSuspend() {
            return true;
        }
    }
}
