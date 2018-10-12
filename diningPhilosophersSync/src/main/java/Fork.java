import java.util.Objects;

public class Fork {

    private int id;

    public Fork(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fork fork = (Fork) o;
        return id == fork.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
