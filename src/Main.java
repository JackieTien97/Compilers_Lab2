import entity.Symbol;
import monitor.Monitor;

import java.util.Stack;

/**
 * 程序主入口
 */
public class Main {
    public static void main(String[] args) {

        // 带分析序列：if a;a; if if a else a
        Stack<Symbol> tokens = new Stack<>();
        tokens.push(Symbol.$R);
        tokens.push(Symbol.a);
        tokens.push(Symbol.ELSE);
        tokens.push(Symbol.a);
        tokens.push(Symbol.IF);
        tokens.push(Symbol.IF);
        tokens.push(Symbol.SEMICOLON);
        tokens.push(Symbol.a);
        tokens.push(Symbol.SEMICOLON);
        tokens.push(Symbol.a);
        tokens.push(Symbol.IF);


        Monitor monitor = new Monitor();
        monitor.analyze(tokens);
    }
}
