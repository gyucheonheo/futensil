package collections;

import collections.FCollections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FCollectionsTest {
    @Test
    void head() {
        Assertions.assertEquals(Optional.of(1), FCollections.head(List.of(1,2,3,4)));
    }
    @Test
    void head1() {
        Assertions.assertEquals(Optional.empty(), FCollections.head(List.of()));
    }
    @Test
    void tail() {
        Assertions.assertEquals(Optional.of(List.of(2,3,4)), FCollections.tail(List.of(1,2,3,4)));
    }
    @Test
    void tail1() {
        Assertions.assertEquals(Optional.empty(), FCollections.tail(List.of()));
    }
    @Test
    void tail2() {
        Assertions.assertEquals(Optional.of(List.of()), FCollections.tail(List.of(1)));
    }
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

    @Test
    void range(){
        Assertions.assertEquals(List.of(0,1,2,3,4), FCollections.range(0, 5));
    }
    @Test
    void range1(){
        Assertions.assertEquals(List.of(), FCollections.range(5, 1));
    }
}
