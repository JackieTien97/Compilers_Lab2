package action;

import entity.Symbol;
import exception.NoItemException;
import stack.CombineStack;


public class ShiftAction implements Action {

    private int state;
    private Symbol symbol;
    private CombineStack combineStack;

    public ShiftAction(int state, Symbol symbol) {
        this.state = state;
        this.symbol = symbol;
        combineStack = CombineStack.COMBINE_STACK;
    }

    @Override
    public void doAction() throws NoItemException {
        combineStack.shift(state, symbol);
    }
}
