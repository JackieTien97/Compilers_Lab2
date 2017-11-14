package entity;

public class Production {
    public Symbol left;
    public int numOfRight;
    public String rightProduction;

    public Production(Symbol left, int numOfRight, String rightProduction) {
        this.left = left;
        this.numOfRight = numOfRight;
        this.rightProduction = rightProduction;
    }

    @Override
    public String toString() {
        return left + "->" + rightProduction;
    }
}
