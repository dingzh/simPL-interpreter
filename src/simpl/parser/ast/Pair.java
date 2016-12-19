package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution sub = rr.s.compose(lr.s);
        return TypeResult.of(sub, new PairType(sub.apply(lr.t), sub.apply(rr.t)));
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        Value lv = l.eval(s);
        Value rv = r.eval(s);
        return new PairValue(lv, rv);
    }
}
