package entity;

/**
 * 所有可能出现的符号
 */
public enum Symbol {
    IF, ELSE, SEMICOLON, a, $R, S;

    @Override
    public String toString() {
        switch (this) {
            case IF:
                return "if";
            case ELSE:
                return "else";
            case SEMICOLON:
                return ";";
            case a:
                return "a";
            case $R:
                return "$R";
            case S:
                return "S";
            default:
                return "error";
        }
    }
}
