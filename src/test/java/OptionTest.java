import data.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionTest {


    @Test
    public void testNone(){
        Option<String> none = Option.none();
        Assertions.assertEquals("none", none.toString());
    }

    @Test
    public void getOrThrowByNoneWillThrowIllegalStateException(){
        Option<String> none = Option.none();
        Exception e = Assertions.assertThrows(IllegalStateException.class, none::getOrThrow);
        Assertions.assertEquals("get called on None", e.getMessage());
    }

    @Test
    public void testSome(){
        Option<String> some = Option.some("Hello World");
        Assertions.assertEquals("Hello World", some.getOrThrow());
    }
}
