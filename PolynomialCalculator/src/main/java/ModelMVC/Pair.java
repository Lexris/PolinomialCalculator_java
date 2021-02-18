package ModelMVC;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Pair<LeftType, RightType> {
    public LeftType left;
    public RightType right;
    public Pair(Class classLeft, Class classRight) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        left = (LeftType) classLeft.getDeclaredConstructor().newInstance();
        right = (RightType) classRight.getDeclaredConstructor().newInstance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<LeftType, RightType> that = (Pair<LeftType, RightType>) o;
        return this.right.equals(that.right) && this.left.equals(that.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
