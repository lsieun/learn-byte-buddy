package sample.hierarchy;

public interface IDog extends IMammal {
    default void sayFromDog() {
        System.out.println("Dog");
    }

    @Override
    default String test(String name, int age) {
        return String.format("Dog: %s - %d", name, age);
    }
}
