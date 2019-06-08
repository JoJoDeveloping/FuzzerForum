package de.jojomodding.fuzzer.ast;


public class NegExpression extends Expression {

    private final Expression e;

    public NegExpression(Expression e){
        this.e = e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NegExpression that = (NegExpression) o;
        return e.equals(that.e);
    }
}
