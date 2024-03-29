package simpl.interpreter;

import simpl.parser.Symbol;

public class Env {

    private final Env E;
    private final Symbol x;
    private final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {

        @Override
        public Value get(Symbol y) {
            return null;
        }

        @Override
        public Env clone() {
            return this;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        if (x.equals(y))
            return v;
        else
            return E.get(y);
    }

    @Override
    public Env clone() {

        return new Env(E.clone(), x, v);
    }
}
