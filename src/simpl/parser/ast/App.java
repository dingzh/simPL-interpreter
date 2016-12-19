package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.snd;
import simpl.interpreter.lib.tl;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        TypeVar tv = new TypeVar(false);
        TypeResult lr = l.typecheck(E);
        TypeResult rr = r.typecheck(E);
        Substitution sub = rr.s.compose(lr.s);
        sub = (new ArrowType(rr.t, tv)).unify(lr.t).compose(sub);
        return TypeResult.of(sub, sub.apply(tv));
    }

    @Override
    public Value eval(State s) throws RuntimeError {

        //FunValue fun = (FunValue) l.eval(s);
        //Value v = r.eval(s);
        //Env env = new Env(fun.E, fun.x, v);
        //return fun.e.eval(State.of(env, s.M, s.p));
        FunValue fun = (FunValue) l.eval(s);
        Value v = r.eval(s), ret;
        if (fun instanceof fst) {
            ret = ((PairValue)v).v1;
        } else if (fun instanceof snd) {
            ret = ((PairValue)v).v2;
        } else if (fun instanceof hd) {
            if (v.equals(Value.NIL)) {
                throw new RuntimeError("hd nil");
            } else {
                ret = ((ConsValue)v).v1;
            }
        } else if (fun instanceof tl) {
            if (v.equals(Value.NIL)) {
                throw new RuntimeError("tl nil");
            } else {
                ret = ((ConsValue)v).v2;
            }
        } else {
            Env env = new Env(fun.E,fun.x,v);
            ret = fun.e.eval(State.of(env,s.M,s.p));
        }
        return ret;
    }
}
