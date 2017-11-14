package stack;

import entity.Production;
import entity.Symbol;
import exception.NoItemException;
import parsingtable.LR1ParsingTable;

import java.util.Stack;

public enum CombineStack {
    COMBINE_STACK;

    private Stack<Integer> stateStack = new Stack<>();
    private Stack<Symbol> symbolStack = new Stack<>();
    private Production[] productions = {
            new Production(Symbol.S, 4, "if S else S"),
            new Production(Symbol.S, 2, "if S"),
            new Production(Symbol.S, 3, "S ; S"),
            new Production(Symbol.S, 1, "a")};

    CombineStack() {
        stateStack.push(0);
        symbolStack.push(Symbol.$R);
    }

    /**
     * 移进操作
     * @param state 需要移进的状态
     * @param symbol 需要移进的符号
     */
    public void shift(int state, Symbol symbol) {
        stateStack.push(state);
        symbolStack.push(symbol);
    }

    /**
     * 规约操作
     * @param production 规约产生式的序号
     */
    public void reduct(int production) throws NoItemException {
        for (int i = 0; i < productions[production-1].numOfRight; i++) {
            stateStack.pop();
            symbolStack.pop();
        }
        symbolStack.push(productions[production-1].left);
        int nextState = LR1ParsingTable.goTo(stateStack.peek(), symbolStack.peek());
        stateStack.push(nextState);
        // 打印规约对应的产生式
        System.out.println(productions[production-1]);
    }

    public int getNowState() {
        return stateStack.peek();
    }
}
