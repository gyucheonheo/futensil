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

    @Test
    public void getOrElseReturnDefaultValueIfItIsNone(){
        String defaultValue = (String) Option.none().getOrElse(() -> "Default");
        Assertions.assertEquals("Default", defaultValue);
    }

    @Test
    public void mapConvertsOptionAtoOptionB(){
        Assertions.assertEquals(5, Option.some("Hello").map(String::length).getOrElse(() -> -1));
    }
}
