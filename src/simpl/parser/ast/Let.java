package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Let extends Expr {

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult tr1 = e1.typecheck(E);
        TypeResult tr2 = e2.typecheck(TypeEnv.of(E, x, tr1.t));
        Substitution sub = tr2.s.compose(tr1.s);
        return TypeResult.of(sub, sub.apply(tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        Value v1 = e1.eval(s);
        State ns = State.of(new Env(s.E, x, v1), s.M, s.p);
        return e2.eval(ns);
    }
}
