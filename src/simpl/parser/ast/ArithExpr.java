package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution sub = rr.s.compose(lr.s);
        sub = sub.compose(lr.t.unify(Type.INT));
        sub = sub.compose(rr.t.unify(Type.INT));
        return TypeResult.of(sub, Type.INT);
    }
}
