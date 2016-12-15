package simpl.interpreter;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.prefs.BackingStoreException;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BoolValue)
            return this.b == ((BoolValue) other).b;
        return false;
    }
}
