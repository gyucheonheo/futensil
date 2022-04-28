package collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {
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
}
