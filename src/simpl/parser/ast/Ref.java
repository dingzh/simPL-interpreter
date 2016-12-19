package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult tr = e.typecheck(E);
        return TypeResult.of(tr.s, new RefType(tr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        int p = s.p.get();
        s.M.put(p, e.eval(s));
        s.p.set(p + 1);
        return new RefValue(p);
    }
}
