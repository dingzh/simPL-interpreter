package simpl.interpreter;

import static simpl.parser.Symbol.symbol;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {

        Env fstEnv = new Env(E, symbol("fst"), new fst());
        Env sndEnv = new Env(fstEnv, symbol("snd"), new snd());
        Env hdEnv = new Env(sndEnv, symbol("hd"), new hd());
        Env tlEnv = new Env(hdEnv, symbol("tl"), new tl());
        Env succEnv = new Env(tlEnv, symbol("succ"), new succ());
        Env iszeroEnv = new Env(succEnv, symbol("iszero"), new iszero());
        Env predEnv = new Env(iszeroEnv, symbol("pred"), new pred());
        return predEnv;
    }
}
