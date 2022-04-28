import collections.FCollections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FCollectionsTest {

    @Test
    void reverse(){
        Assertions.assertEquals(List.of(3,2,1), FCollections.reverse(List.of(1,2,3)));
    }

    @Test
    void reverseEmptyList(){
        Assertions.assertEquals(List.of(), FCollections.reverse(List.of()));
    }

    @Test
    void reverseSingleton(){
        Assertions.assertEquals(List.of("a"), FCollections.reverse(List.of("a")));
    }


    @Test
    void maps(){
        Function<Integer, Integer> f = a -> a * 3;
        Function<Integer, Integer> f1 = a -> a *5;

        Assertions.assertEquals(List.of(15,15,15), FCollections.maps(List.of(1,2,3), f, f1));
    }
}
