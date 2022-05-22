package collections;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FCollections {
    public static <T> List<T> list(){
        return Collections.emptyList();
    }

    public static <T> List<T> list(T t){
        return Collections.singletonList(t);
    }

    public static <T> List<T> list(List<T> ts){
        return List.copyOf(ts);
    }

    public static <T> List<T> list(T... t){
        return List.of(Arrays.copyOf(t, t.length));
    }

    public static <T> Optional<T> head(List<T> list){
        if(list.isEmpty()) return Optional.empty();
        else return Optional.of(list.get(0));
    }

    public static <T> Optional<List<T>> tail(List<T> list){
        if(list.isEmpty()) return Optional.empty();
        return Optional.of(
                List.copyOf(list.subList(
                        1, list.size()
                ))
        );
    }

    public static <T> List<T> append(List<T> list, T t){
        List<T> ts = new ArrayList<>(list);
        ts.add(t);
        return List.copyOf(ts);
    }

    public static <T> List<T> prepend(T t, List<T> list){
        return foldLeft(list, list(t), a -> b -> append(a, b));
    }

    public static <T> T fold(List<T> ts, T identity, Function<T, Function<T, T>> f){
        T result = identity;
        for(T t: ts){
            result = f.apply(result).apply(t);
        }
        return result;
    }

    public static <T, U> U foldLeft(List<T> ts, U identity, Function<U, Function<T, U>> f) {
        U result = identity;
        for (T t : ts) {
            result = f.apply(result).apply(t);
        }
        return result;
    }

        private static <T, U> List<U> mapViaFoldLeft(List<T> ts, Function<T, U> f){
            return foldLeft(ts, list(), a -> b -> append(a, f.apply(b)));
        }

    public static <T, U> U foldRight(List<T> ts, U identity, Function<T, Function<U, U>> f){
        U result = identity;
        for(int i = ts.size()-1; i >= 0; i--){
            result = f.apply(ts.get(i)).apply(result);
        }
        return result;

    }

    public static <T> List<T> reverse(List<T> list){
        return foldLeft(list, list(), a -> b -> prepend(b, a));
    }

    public static <T> List<T> maps(List<T> ts, Function<T, T>... fs){
        Function<T, T> finalFunction = Arrays.stream(fs)
                                        .reduce( Function.identity(), Function::andThen);
        return ts.stream().map(finalFunction).collect(Collectors.toUnmodifiableList());
    }

    public static <T> void forEach(Collection<T> ts, Consumer<T> e){
        for(T t: ts) e.accept(t);
    }

    public static <T> List<T> unfold(T seed, Function<T, T> f, Function<T, Boolean> p){
       List<T> ts = new ArrayList<>();
       T temp = seed;
       while(p.apply(temp)){
           ts = append(ts, temp);
           temp = f.apply(temp);
       }
       return ts;
    }

    public static List<Integer> range(int start, int end){
        return unfold(start, x-> x+ 1, x -> x < end);
    }
}
