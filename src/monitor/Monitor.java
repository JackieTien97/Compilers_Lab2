package monitor;

import action.Action;
import action.ShiftAction;
import entity.Symbol;
import exception.NoItemException;
import parsingtable.LR1ParsingTable;
import stack.CombineStack;

import java.util.Stack;

public class Monitor {

    private CombineStack combineStack;

    public Monitor() {
        this.combineStack = CombineStack.COMBINE_STACK;
    }

    public void analyze(Stack<Symbol> tokens) {
        while (!tokens.empty()) {
            try {
                Action action = LR1ParsingTable.action(combineStack.getNowState(), tokens.peek());
                action.doAction();
                if (action instanceof ShiftAction) {
                    tokens.pop();
                }
            } catch (NoItemException e) {
                System.err.println(e.getMessage());
                break;
            }
        }
        System.err.println("Failed!!");
    }
}
