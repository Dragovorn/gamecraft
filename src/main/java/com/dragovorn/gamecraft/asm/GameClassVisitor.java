package com.dragovorn.gamecraft.asm;

import com.dragovorn.gamecraft.annotation.CraftGame;
import com.dragovorn.gamecraft.discovery.Game;
import com.dragovorn.gamecraft.discovery.GameInfo;
import com.dragovorn.gamecraft.util.StringUtil;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class GameClassVisitor extends ClassVisitor {

    private GameInfo.Builder builder;

    private boolean extendsGame;

    private String name;

    public GameClassVisitor(GameInfo.Builder builder) {
        super(Opcodes.ASM6);

        this.builder = builder;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.name = name;
        this.extendsGame = superName.equals(StringUtil.formatClassPath(Game.class));

        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (desc.equals("L" + StringUtil.formatClassPath(CraftGame.class) + ";")) {
            if (this.extendsGame) {
                this.builder.setMain(this.name.replaceAll("/", ".")); // ONLY FOR TESTING, NEED TO RETARD PROOF THIS
                return new CraftGameAnnotationVisitor(this.builder);
            }
        }

        return super.visitAnnotation(desc, visible);
    }
}