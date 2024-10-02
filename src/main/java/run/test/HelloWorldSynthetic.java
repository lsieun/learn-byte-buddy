package run.test;

import lsieun.utils.ASMUtils;
import lsieun.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

public class HelloWorldSynthetic {
    public static void main(String[] args) throws IOException {
        Path[] pathArray = {
                FileUtils.getFilePath("sample/HelloWorld.class"),
        };

        System.out.println("Remove Synthetic:");
        for (Path filepath : pathArray) {
            ASMUtils.removeSynthetic(filepath);
        }
    }
}
