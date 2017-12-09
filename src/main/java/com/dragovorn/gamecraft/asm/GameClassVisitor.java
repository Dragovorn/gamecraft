package com.dragovorn.gamecraft.asm;

import com.dragovorn.gamecraft.annotation.CraftCommand;
import com.dragovorn.gamecraft.annotation.CraftGame;
import com.dragovorn.gamecraft.annotation.PlayerType;
import com.dragovorn.gamecraft.command.CommandExecutor;
import com.dragovorn.gamecraft.discovery.Game;
import com.dragovorn.gamecraft.discovery.info.GameInfo;
import com.dragovorn.gamecraft.player.GamePlayer;
import com.dragovorn.gamecraft.util.StringUtil;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class GameClassVisitor extends ClassVisitor {

    private GameInfo.Builder builder;

    private String name;

    private boolean extendsGame;
    private boolean implementsExecutor;
    private boolean extendsGamePlayer;

    public GameClassVisitor(GameInfo.Builder builder) {
        super(Opcodes.ASM6);

        this.builder = builder;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.name = name.replaceAll("/", ".");
        this.extendsGame = superName.equals(StringUtil.formatClassPath(Game.class));
        this.implementsExecutor = Arrays.stream(interfaces).anyMatch(str -> str.equals(StringUtil.formatClassPath(CommandExecutor.class)));
        this.extendsGamePlayer = superName.equals(StringUtil.formatClassPath(GamePlayer.class));

        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (matchesAnnotation(desc, CraftGame.class)) {
            if (this.extendsGame && !this.builder.hasMain()) {
                this.builder.setMain(this.name);
                return new CraftGameAnnotationVisitor(this.builder);
            }
        } else if (matchesAnnotation(desc, CraftCommand.class)) {
            if (this.implementsExecutor) {
                return new CraftCommandAnnotationVisitor(this.builder.getCommandInfo().insertNew(this.name));
            }
        } else if (matchesAnnotation(desc, PlayerType.class)) {
            if (this.extendsGamePlayer) {
                this.builder.getPlayerInfo().setPlayerTypePath(this.name);
            }
        }

        return super.visitAnnotation(desc, visible);
    }

    private boolean matchesAnnotation(String desc, Class<? extends Annotation> annotation) {
        return desc.equals("L" + StringUtil.formatClassPath(annotation) + ";");
    }
}