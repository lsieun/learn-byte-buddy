package lsieun.utils;

import lsieun.buddy.asm.modifier.RemoveSyntheticVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;


import java.io.File;

public class ASMUtils {
    public static void removeSynthetic(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            return;
        }

        byte[] bytes = FileUtils.readBytes(filepath);
        if (bytes == null) return;


        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new RemoveSyntheticVisitor(Opcodes.ASM9, cw);
        cr.accept(cv, 0);

        // String className = cr.getClassName();
        byte[] newBytes = cw.toByteArray();
        FileUtils.writeBytes(filepath, newBytes);
        // OutputUtils.printFilePath(className, filepath);
    }
}
