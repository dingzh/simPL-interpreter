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

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution sub = rr.s.compose(lr.s);
        sub = sub.compose(lr.t.unify(Type.BOOL));
        sub = sub.compose(rr.t.unify(Type.BOOL));
        return TypeResult.of(sub, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        BoolValue lv = (BoolValue) l.eval(s);
        BoolValue rv = (BoolValue) r.eval(s);
        return new BoolValue(lv.b || rv.b);
    }
}
