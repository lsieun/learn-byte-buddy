package sample.hierarchy;

public interface ICat extends IMammal {
    default void sayFromCat() {
        System.out.println("Cat");
    }

    @Override
    default String test(String name, int age) {
        return String.format("Cat: %s - %d", name, age);
    }
}
