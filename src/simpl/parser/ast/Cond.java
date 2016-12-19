package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult t1 = e1.typecheck(E);
        TypeResult t2 = e2.typecheck(E);
        TypeResult t3 = e3.typecheck(E);
        Substitution sub = t3.s.compose(t2.s.compose(t1.s));
        sub = sub.compose(t1.t.unify(Type.BOOL));
        sub = sub.compose(t3.t.unify(t2.t));
        return TypeResult.of(sub, sub.apply(t2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        BoolValue v1 = (BoolValue) e1.eval(s);
        return v1.b ? e2.eval(s) : e3.eval(s);
    }
}
