package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;
import sun.reflect.generics.tree.TypeSignature;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        Type t = E.get(x);
        if (t == null)
            throw new TypeError("Name");
        else
            return TypeResult.of(t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        Value v = s.E.get(x);
        if (v instanceof RecValue) {
            RecValue rv = (RecValue) v;
            State rs = State.of(rv.E, s.M, s.p);
            Rec rc = new Rec(rv.x, rv.e);
            return rc.eval(rs);
        } else {
            return v;
        }
    }
}
