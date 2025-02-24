package lsieun.buddy.description;

import lsieun.box.BoxUtils;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;

import java.lang.reflect.Modifier;

public class DescriptionForField {
    public static void print(FieldDescription fieldDesc) {
        TypeDefinition declaringType = fieldDesc.getDeclaringType();
        int actualModifiers = fieldDesc.getActualModifiers();
        TypeDescription.Generic type = fieldDesc.getType();
        String name = fieldDesc.getName();
        FieldDescription.SignatureToken signatureToken = fieldDesc.asSignatureToken();
        String descriptor = fieldDesc.getDescriptor();

        String[][] matrix = {
                {"getDeclaringType()", declaringType.getTypeName()},
                {"getActualModifiers()", Modifier.toString(actualModifiers)},
                {"getType()", type.getTypeName()},
                {"getName()", name},
                {"asSignatureToken()", String.valueOf(signatureToken)},
                {"getDescriptor()", descriptor},
        };

        BoxUtils.printMatrix(matrix);
    }
}
