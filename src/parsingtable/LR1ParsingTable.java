package parsingtable;

import action.AcceptAction;
import action.Action;
import action.ReductAction;
import action.ShiftAction;
import entity.ActionItem;
import entity.ActionType;
import entity.Symbol;
import exception.NoItemException;

import java.util.HashMap;
import java.util.Map;

/**
 * LR(1) Parsing table
 *  ________________________________________
 * |      |       Action           |  GOTO  |
 *  states ----------------------------------
 * |      |  if | else| ; |a  |$R  |    S   |
 *  ----------------------------------------
 * |   0  |  S2 |     |   |S3 |    |    1   |
 *  ----------------------------------------
 * |   1  |     |     | S4|   |Acc |        |
 *  ----------------------------------------
 * |   2  |  S6 |     |   |S7 |    |    5   |
 *  ----------------------------------------
 * |   3  |     |     | r4|   |r4  |        |
 *  ----------------------------------------
 * |   4  |  S2 |     |   |S3 |    |    8   |
 *  ----------------------------------------
 * |   5  |     | S9  |S10|   |r2  |        |
 *  ----------------------------------------
 * |   6  |  S6 |     |   |S7 |    |    11  |
 *  ----------------------------------------
 * |   7  |     | r4  |r4 |   |r4  |        |
 *  ----------------------------------------
 * |   8  |     |     |r3 |   |r3  |        |
 *  ----------------------------------------
 * |   9  |  S2 |     |   |S3 |    |    12  |
 *  ----------------------------------------
 * |   10 |  S2 |     |   |S3 |    |    13  |
 *  ----------------------------------------
 * |   11 |  r2 | S14 |   |   |r2  |        |
 *  ----------------------------------------
 * |   12 |     |     |r1 |   |r1  |        |
 *  ----------------------------------------
 * |   13 |     | r3  |r3 |   |r3  |        |
 *  ----------------------------------------
 * |   14 |  S2 |     |   |S3 |    |    15  |
 *  ----------------------------------------
 * |   15 |  r1 | r1  |   |   |r1  |        |
 *  ----------------------------------------
 */
public class LR1ParsingTable {

    private static Map<String, ActionItem> parsingTable = new HashMap<>();

    static {
        parsingTable.put("0_if", new ActionItem(ActionType.SHIFT, 2));
        parsingTable.put("0_a", new ActionItem(ActionType.SHIFT, 3));
        parsingTable.put("0_S", new ActionItem(ActionType.GOTO, 1));
        parsingTable.put("1_;", new ActionItem(ActionType.SHIFT, 4));
        parsingTable.put("1_$R", new ActionItem(ActionType.ACCEPT, 0));
        parsingTable.put("2_if", new ActionItem(ActionType.SHIFT, 6));
        parsingTable.put("2_a", new ActionItem(ActionType.SHIFT, 7));
        parsingTable.put("2_S", new ActionItem(ActionType.GOTO, 5));
        parsingTable.put("3_;", new ActionItem(ActionType.REDUCT, 4));
        parsingTable.put("3_$R", new ActionItem(ActionType.REDUCT, 4));
        parsingTable.put("4_if", new ActionItem(ActionType.SHIFT, 2));
        parsingTable.put("4_a", new ActionItem(ActionType.SHIFT, 3));
        parsingTable.put("4_S", new ActionItem(ActionType.GOTO, 8));
        parsingTable.put("5_else", new ActionItem(ActionType.SHIFT, 9));
        parsingTable.put("5_;", new ActionItem(ActionType.SHIFT, 10));
        parsingTable.put("5_$R", new ActionItem(ActionType.REDUCT, 2));
        parsingTable.put("6_if", new ActionItem(ActionType.SHIFT, 6));
        parsingTable.put("6_a", new ActionItem(ActionType.SHIFT, 7));
        parsingTable.put("6_S", new ActionItem(ActionType.GOTO, 11));
        parsingTable.put("7_else", new ActionItem(ActionType.REDUCT, 4));
        parsingTable.put("7_;", new ActionItem(ActionType.REDUCT, 4));
        parsingTable.put("7_$R", new ActionItem(ActionType.REDUCT, 4));
        parsingTable.put("8_;", new ActionItem(ActionType.REDUCT, 3));
        parsingTable.put("8_$R", new ActionItem(ActionType.REDUCT, 3));
        parsingTable.put("9_if", new ActionItem(ActionType.SHIFT, 2));
        parsingTable.put("9_a", new ActionItem(ActionType.SHIFT, 3));
        parsingTable.put("9_S", new ActionItem(ActionType.GOTO, 12));
        parsingTable.put("10_if", new ActionItem(ActionType.SHIFT, 2));
        parsingTable.put("10_a", new ActionItem(ActionType.SHIFT, 3));
        parsingTable.put("10_S", new ActionItem(ActionType.GOTO, 13));
        parsingTable.put("11_if", new ActionItem(ActionType.REDUCT, 2));
        parsingTable.put("11_else", new ActionItem(ActionType.SHIFT, 14));
        parsingTable.put("11_$R", new ActionItem(ActionType.REDUCT, 2));
        parsingTable.put("12_;", new ActionItem(ActionType.REDUCT, 1));
        parsingTable.put("12_$R", new ActionItem(ActionType.REDUCT, 1));
        parsingTable.put("13_else", new ActionItem(ActionType.REDUCT, 3));
        parsingTable.put("13_;", new ActionItem(ActionType.REDUCT, 3));
        parsingTable.put("13_$R", new ActionItem(ActionType.REDUCT, 3));
        parsingTable.put("14_if", new ActionItem(ActionType.SHIFT, 2));
        parsingTable.put("14_a", new ActionItem(ActionType.SHIFT, 3));
        parsingTable.put("14_S", new ActionItem(ActionType.GOTO, 15));
        parsingTable.put("15_if", new ActionItem(ActionType.REDUCT, 1));
        parsingTable.put("15_else", new ActionItem(ActionType.REDUCT, 1));
        parsingTable.put("15_$R", new ActionItem(ActionType.REDUCT, 1));
    }


    /**
     * 返回GOTO中的下一个状态
     * @param nowState 当前状态
     * @param symbol 当前非终结符
     * @return 若存在这项，返回对应的状态号
     * @throws NoItemException 若不存在，则抛出一个异常
     */
    public static int goTo(int nowState, Symbol symbol) throws NoItemException {
        String key = String.valueOf(nowState) + "_" + symbol.toString();
        if (parsingTable.containsKey(key) && parsingTable.get(key).actionType == ActionType.GOTO) {
            return parsingTable.get(key).appendix;
        }
        else {
            throw new NoItemException("There is no " + key + " item in LR(1)ParsingTable GOTO columns");
        }
    }


    /**
     * 返回一个Action
     * @param nowState 当前状态
     * @param symbol 当前终结符
     * @return 若存在这项，返回对应的Action
     * @throws NoItemException 若不存在，则抛出一个异常
     */
    public static Action action(int nowState, Symbol symbol) throws NoItemException {
        String key = String.valueOf(nowState) + "_" + symbol.toString();
        if (parsingTable.containsKey(key)) {
            if (parsingTable.get(key).actionType == ActionType.SHIFT) {
                return new ShiftAction(parsingTable.get(key).appendix, symbol);
            }
            else if (parsingTable.get(key).actionType == ActionType.REDUCT) {
                return new ReductAction(parsingTable.get(key).appendix);
            }
            else {
                return new AcceptAction();
            }
        }
        else {
            throw new NoItemException("There is no " + key + " item in LR(1)ParsingTable ACTION columns");
        }
    }
}
