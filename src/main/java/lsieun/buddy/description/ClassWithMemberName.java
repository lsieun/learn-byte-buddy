package lsieun.buddy.description;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClassWithMemberName {
    private final Class<?> clazz;

    private final List<String> nameList;

    private ClassWithMemberName(Class<?> clazz, List<String> nameList) {
        this.clazz = clazz;
        this.nameList = nameList;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public static ClassWithMemberName of(Class<?> clazz, String... nameArray) {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(nameArray);
        if (nameArray.length == 0) {
            throw new IllegalArgumentException("nameArray length is 0");
        }

        return of(clazz, Arrays.asList(nameArray));
    }

    public static ClassWithMemberName of(Class<?> clazz, List<String> nameList) {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(nameList);
        if (nameList.isEmpty()) {
            throw new IllegalArgumentException("nameList is empty");
        }
        return new ClassWithMemberName(clazz, nameList);
    }
}
