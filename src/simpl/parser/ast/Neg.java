package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult tr = e.typecheck(E);
        Substitution sub = tr.s.compose(tr.t.unify(Type.INT));
        return TypeResult.of(sub, Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        IntValue v = (IntValue) e.eval(s);
        return new IntValue(-v.n);
    }
}
