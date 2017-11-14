package action;

import exception.NoItemException;
import stack.CombineStack;

public class ReductAction implements Action {

    // 对应产生式的序号
    private int production;

    private CombineStack combineStack;

    public ReductAction(int production) {
        this.production = production;
        combineStack = CombineStack.COMBINE_STACK;
    }

    @Override
    public void doAction() throws NoItemException {
        combineStack.reduct(production);
    }
}
