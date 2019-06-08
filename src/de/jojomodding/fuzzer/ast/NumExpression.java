package de.jojomodding.fuzzer.ast;

import java.math.BigInteger;

public class NumExpression extends Expression {

    private final BigInteger num;

    public NumExpression(BigInteger bi){
        this.num = bi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumExpression that = (NumExpression) o;
        return num.equals(that.num);
    }
}
