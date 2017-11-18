package com.dragovorn.gamecraft.asm;

import com.dragovorn.gamecraft.command.RawCommand;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class CraftCommandAnnotationVisitor extends AnnotationVisitor {

    private RawCommand command;

    CraftCommandAnnotationVisitor(RawCommand command) {
        super(Opcodes.ASM6);

        this.command = command;
    }

    @Override
    public void visit(String name, Object value) {
        switch (name) {
            case "value":
                this.command.setName((String) value);
                break;
            case "child":
                this.command.setChild((boolean) value);
                break;
        }

        super.visit(name, value);
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        return new ArrayVisitor(name);
    }

    private class ArrayVisitor extends AnnotationVisitor {

        private String name;

        private ArrayVisitor(String name) {
            super(Opcodes.ASM6);

            this.name = name;
        }

        @Override
        public void visit(String name, Object value) {
            switch (this.name) {
                case "aliases":
                    command.addAlias((String) value);
                    break;
                case "children":
                    command.addChild(((Type) value).getClassName());
                    break;
            }
        }
    }
}