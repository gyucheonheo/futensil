package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TailCallTest {

    @Test
    public void test_itDoesNotThrow_StackOverFlowException(){
        TailCall<Integer> sum = add(3, 1000000000);
        while(sum.isSuspend()){
            sum = sum.resume();
        }
        Assertions.assertEquals(1000000003, sum.eval());
    }

        private TailCall<Integer> add(int x, int y){
            return y == 0? new TailCall.Return<>(x) : new TailCall.Suspend<>( () -> add(x+1, y-1));
        }
}
