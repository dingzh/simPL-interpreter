package simpl.typing;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        E = TypeEnv.of(TypeEnv.empty, Symbol.symbol("iszero"), new ArrowType(Type.INT, Type.BOOL));
        E = TypeEnv.of(E, Symbol.symbol("pred"), new ArrowType(Type.INT, Type.INT));
        E = TypeEnv.of(E, Symbol.symbol("succ"), new ArrowType(Type.INT, Type.INT));
        TypeVar a = new TypeVar(true);
        TypeVar b = new TypeVar(true);
        TypeVar c = new TypeVar(true);
        TypeVar d = new TypeVar(true);
        TypeVar e = new TypeVar(true);
        TypeVar f = new TypeVar(true);
        E = TypeEnv.of(E, Symbol.symbol("fst"), new ArrowType(new PairType(a, b), a));
        E = TypeEnv.of(E, Symbol.symbol("snd"), new ArrowType(new PairType(c, d), d));
        E = TypeEnv.of(E, Symbol.symbol("hd"),  new ArrowType(new ListType(e), e));
        E = TypeEnv.of(E, Symbol.symbol("tl"),  new ArrowType(new ListType(f), new ListType(f)));
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
