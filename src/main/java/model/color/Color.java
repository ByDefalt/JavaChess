package model.color;

public abstract class Color {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
