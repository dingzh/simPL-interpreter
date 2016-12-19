package simpl.typing;

public final class PairType extends Type {

    public Type t1, t2;

    public PairType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            return ((TypeVar) t).unify(this);
        } else if (t instanceof PairType) {
            PairType ptt = (PairType) t;
            Substitution sub1 = t1.unify(ptt.t1);
            Substitution sub2 = t2.unify(ptt.t2);
            return sub2.compose(sub1);
        } else
            throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return this.t1.contains(tv) || this.t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new PairType(this.t1.replace(a, t), this.t2.replace(a, t));
    }

    public String toString() {
        return "(" + t1 + " * " + t2 + ")";
    }
}
