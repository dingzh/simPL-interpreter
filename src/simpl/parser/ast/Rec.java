package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        Type t = new TypeVar(false);
        E = TypeEnv.of(E, x, t);
        TypeResult tr = e.typecheck(E);
        t = tr.s.apply(t);
        Substitution s = t.unify(tr.t).compose(tr.s);
        return TypeResult.of(s, s.apply(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        Value v = new RecValue(s.E,x,e);
        Env env = new Env(s.E,x,v);
        return e.eval(State.of(env,s.M,s.p));
    }
}
