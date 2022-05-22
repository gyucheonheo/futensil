package data;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Option<A> {

    @SuppressWarnings("rawtypes")
    private static Option none = new None();
    public abstract A getOrThrow();
    public abstract A getOrElse(Supplier<A> defaultValue);
    public abstract <B> Option<B> map(Function<A, B> f);
    public abstract <B> Option<B> flatMap(Function<A, Option<B>> f);

    private static class None<A> extends Option<A> {

        @Override
        public A getOrThrow() {
            throw new IllegalStateException("get called on None");
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return none();
        }

        @Override
        public <B> Option<B> flatMap(Function<A, Option<B>> f) {
            return map(f).getOrElse(Option::none);
        }



        @Override
        public String toString(){
            return "none";
        }
    }

    private static class Some<A> extends Option<A>{
        private final A value;
        private Some(A a){
            this.value = a;
        }
        @Override
        public A getOrThrow() {
            return value;
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return value;
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return Option.some(f.apply(value));
        }

        @Override
        public <B> Option<B> flatMap(Function<A, Option<B>> f) {
            return f.apply(value);
        }

        @Override
        public String toString(){
            return String.format("Some(%s)", this.value);
        }
    }

    public static <A> Option<A> some(A a){
        return new Some<>(a);
    }

    @SuppressWarnings("unchecked")
    public static <A> Option<A> none() {
        return none;
    }
}
