package de.jojomodding.fuzzer.ast;

public class BinaryOpExpression extends Expression{

    public enum OP{
        PLUS, MINUS, TIMES, DIVIDE;
    }

    private final Expression l,r;
    private final OP op;

    public BinaryOpExpression(Expression l, OP o, Expression r){
        this.l = l;
        this.r = r;
        this.op = o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOpExpression that = (BinaryOpExpression) o;
        return l.equals(that.l) &&
                r.equals(that.r) &&
                op == that.op;
    }
}
