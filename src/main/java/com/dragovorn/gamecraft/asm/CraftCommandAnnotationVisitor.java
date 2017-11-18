package com.dragovorn.gamecraft.asm;

import com.dragovorn.gamecraft.command.RawCommand;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

public class CraftCommandAnnotationVisitor extends AnnotationVisitor {

    private RawCommand command;

    public CraftCommandAnnotationVisitor(RawCommand command) {
        super(Opcodes.ASM6);

        this.command = command;
    }

    @Override
    public void visit(String name, Object value) {
        this.command.setName((String) value);
    }
}