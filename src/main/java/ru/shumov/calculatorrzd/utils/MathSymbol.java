package ru.shumov.calculatorrzd.utils;

import java.util.Objects;

public class MathSymbol {
    protected String value;

    protected MathSymbolType type;

    public MathSymbol(String value, MathSymbolType mathSymbolType) {
        this.value = value;
        this.type = mathSymbolType;
    }

    public MathSymbol(Character value, MathSymbolType mathSymbolType) {
        this.value = value.toString();
        this.type = mathSymbolType;
    }

    public String getValue() {
        return value;
    }

    public MathSymbolType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathSymbol that)) return false;
        return Objects.equals(getValue(), that.getValue()) && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getType());
    }

    @Override
    public String toString() {
        return "MathSymbol{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
