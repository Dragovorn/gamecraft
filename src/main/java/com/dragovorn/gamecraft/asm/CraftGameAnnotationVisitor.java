package com.dragovorn.gamecraft.asm;

import com.dragovorn.gamecraft.discovery.GameInfo;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

public class CraftGameAnnotationVisitor extends AnnotationVisitor {

    private GameInfo.Builder builder;

    public CraftGameAnnotationVisitor(GameInfo.Builder builder) {
        super(Opcodes.ASM6);

        this.builder = builder;
    }

    @Override
    public void visit(String name, Object value) {
        switch (name) {
            case "name":
                this.builder.setName((String) value);
                break;
            case "author":
                this.builder.setAuthor((String) value);
                break;
            case "version":
                this.builder.setVersion((String) value);
                break;
            case "supportedVersions":
                this.builder.setSupportedVersions((String) value);
                break;
        }

        super.visit(name, value);
    }
}