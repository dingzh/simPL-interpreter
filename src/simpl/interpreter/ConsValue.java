package simpl.interpreter;

import simpl.parser.ast.Cons;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "list@" + this.getLength();
    }

    public int getLength() {
        if (v2 instanceof ConsValue)
            return ((ConsValue) v2).getLength() + 1;
        else
            return 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof  ConsValue)
            return this.v1.equals(((ConsValue) other).v1) && this.v2.equals(((ConsValue) other).v2);
        return false;
    }
}
