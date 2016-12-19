package simpl.typing;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        E = TypeEnv.empty;
        E = TypeEnv.of(E, Symbol.symbol("iszero"), new ArrowType(Type.INT, Type.BOOL));
        E = TypeEnv.of(E, Symbol.symbol("pred"), new ArrowType(Type.INT, Type.INT));
        E = TypeEnv.of(E, Symbol.symbol("succ"), new ArrowType(Type.INT, Type.INT));

        // todo does every predefined fun need different type var?
        TypeVar a = new TypeVar(true);
        TypeVar b = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("fst"), new ArrowType(new PairType(a, b), a));

        TypeVar c = new TypeVar(true);
        TypeVar d = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("snd"), new ArrowType(new PairType(c, d), d));

        TypeVar e = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("hd"),  new ArrowType(new ListType(e), e));
        TypeVar f = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("tl"),  new ArrowType(new ListType(f), new ListType(f)));
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
