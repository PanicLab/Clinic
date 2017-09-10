package com.github.paniclab.models;

import java.io.Serializable;
import java.util.Objects;

public abstract class PersistableObject implements Serializable {
    private long id;

    protected PersistableObject() {
        this(-1L);
    }

    protected PersistableObject(long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (hashCode() != obj.hashCode()) return false;
        if (isNot(obj instanceof PersistableObject)) return false;
        if(isNot(this.getClass().equals(obj.getClass()))) return false;
        if (isNot(this.id == PersistableObject.class.cast(obj).getId())) return false;
        return true;
    }

    @Override
    public abstract String toString();


    protected boolean isNot(boolean b) {
        return !b;
    }
}
