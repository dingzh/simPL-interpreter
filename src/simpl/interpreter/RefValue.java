package simpl.interpreter;

public class RefValue extends Value {

    public final int p;
    public final Value content;

    public RefValue(int p, Value content) {
        this.p = p;
        this.content = content;
    }

    public String toString() {
        return "ref@" + content;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof RefValue)
            return p == ((RefValue) other).p;
        return false;
    }
}
