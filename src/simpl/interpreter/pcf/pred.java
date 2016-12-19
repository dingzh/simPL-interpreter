package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.*;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class pred extends FunValue {

    public pred() {
        super(Env.empty, Symbol.symbol("predx"), new Cond(null, null,null));
        ((Cond) e).e1 = new Greater(new Name(x),new IntegerLiteral(0));
        ((Cond) e).e2 = new Sub(new Name(x),new IntegerLiteral(1));
        ((Cond) e).e3 = new IntegerLiteral(0);
    }


}
