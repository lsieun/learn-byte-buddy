package sample.hierarchy;

public interface IMammal extends IAnimal {
    default void sayFromMammal() {
        System.out.println("Mammal");
    }

    @Override
    default String test(String name, int age) {
        return String.format("Mammal: %s - %d", name, age);
    }
}