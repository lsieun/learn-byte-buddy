package lsieun.utils;

import lsieun.buddy.asm.modifier.RemoveSyntheticVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ASMUtils {
    public static void removeSynthetic(Path filepath) throws IOException {
        // (1) read bytes
        if (!Files.exists(filepath) || Files.isDirectory(filepath)) {
            String msg = String.format("please check filepath: %s", filepath);
            System.out.println(msg);
            return;
        }
        byte[] bytes = Files.readAllBytes(filepath);

        // (2) remove synthetic
        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new RemoveSyntheticVisitor(Opcodes.ASM9, cw);
        cr.accept(cv, 0);

        // (3) save
        byte[] newBytes = cw.toByteArray();
        Files.write(filepath, newBytes);
    }
}
