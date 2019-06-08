package de.jojomodding.fuzzer;

import de.jojomodding.fuzzer.ast.BinaryOpExpression;
import de.jojomodding.fuzzer.ast.Expression;
import de.jojomodding.fuzzer.ast.NegExpression;
import de.jojomodding.fuzzer.ast.NumExpression;

import java.math.BigInteger;
import java.util.Random;

import static de.jojomodding.fuzzer.ast.BinaryOpExpression.*;

public class Fuzzer {

    private Random random = new Random(54321);

    /*
    Hier steht der letzte generierte Teilausdruck drinnen
     */
    private Expression lastExpr;

    public String exp(){
        String s = mexp();
        Expression le = lastExpr;
        switch (random.nextInt(4)){ //Entweder 0, 1, 2 oder 3
            case 0:
                String res = s+"+"+exp();
                lastExpr = new BinaryOpExpression(le, OP.PLUS, lastExpr);
                return res;
            case 1:
                res = s+"-"+exp();
                lastExpr = new BinaryOpExpression(le, OP.MINUS, lastExpr);
                return res;
            default: //case 2 und 3, also doppelt so wahrscheinlich
                return s;
        }
    }

    private String mexp(){
        String s = uexp();
        Expression le = lastExpr;
        switch (random.nextInt(4)){
            case 0:
                String res = s+"*"+mexp();
                lastExpr = new BinaryOpExpression(le, OP.TIMES, lastExpr);
                return res;
            case 1:
                res = s+"/"+mexp();
                lastExpr = new BinaryOpExpression(le, OP.DIVIDE, lastExpr);
                return res;
            default:
                return s;
        }
    }

    private String uexp(){
        if(random.nextBoolean()){
            String res = "~"+pexp();
            lastExpr = new NegExpression(lastExpr);
            return res;
        }else{
            return pexp();
        }
    }

    private String pexp(){
        if(random.nextInt(5)!=1){ //Wahrscheinlichkeit 4/5
            return num();
        }else{
            return "("+exp()+")";
        }
    }

    private String num(){
        if(random.nextInt(10)==1){ //Wahrscheinlickeit 1/10
            lastExpr = new NumExpression(BigInteger.ZERO);
            return "0";
        }
        int numStart = random.nextInt(9);
        BigInteger bi = BigInteger.valueOf(1+numStart);

        String result = ""+(char)('1'+numStart); //Zahl zwischen 0 und 8 + '1' -> Zeichen zwischen '1' und '9'
        while (random.nextBoolean()){
            int nextDigit = random.nextInt(10);
            bi = bi.multiply(BigInteger.TEN).add(BigInteger.valueOf(nextDigit)); //bi=10*bi+i
            result = result + (char)('0'+nextDigit);
        }
        lastExpr = new NumExpression(bi);
        return result;
    }

    public Expression getLastExpression(){
        return lastExpr;
    }

    public static void main(String[] args){
        Fuzzer f = new Fuzzer();
        for(int i = 0; i < 5; i++){
            System.out.println(f.exp());
        }
    }

}
