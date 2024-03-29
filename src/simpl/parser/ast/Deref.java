package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult tr = e.typecheck(E);
        if (tr.t instanceof RefType) {
            return TypeResult.of(tr.s, ((RefType) tr.t).t);
        } else {
            throw new TypeError("Deref");
        }
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        RefValue v = (RefValue) e.eval(s);
        return s.M.get(v.p);
    }
}
