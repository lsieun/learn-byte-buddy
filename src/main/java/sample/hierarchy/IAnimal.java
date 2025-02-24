package sample.hierarchy;

public interface IAnimal extends ITest {
    default void sayFromAnimal() {
        System.out.println("Animal");
    }

    @Override
    default String test(String name, int age) {
        return String.format("Animal: %s - %d", name, age);
    }
}