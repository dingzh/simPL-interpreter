package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution sub = rr.s.compose(lr.s);
        sub = sub.compose(lr.t.unify(new RefType(rr.t)));
        return TypeResult.of(sub, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        RefValue lv = (RefValue) l.eval(s);
        Value rv = r.eval(s);
        s.M.put(lv.p, rv);
        return Value.UNIT;
    }
}
