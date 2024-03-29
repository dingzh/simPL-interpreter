package simpl.typing;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {

        if (t instanceof TypeVar) {
            return ((TypeVar ) t).unify(this);
        } else if (t instanceof ArrowType) {
            ArrowType att = (ArrowType) t;
            Substitution sub1 = this.t1.unify(att.t1);
            Substitution sub2 = this.t2.unify(att.t2);
            return sub2.compose(sub1);
        } else {
            throw new TypeMismatchError();
        }
    }

    @Override
    public boolean contains(TypeVar tv) {

        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {

        return new ArrowType(t1.replace(a,t), t2.replace(a,t));
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
