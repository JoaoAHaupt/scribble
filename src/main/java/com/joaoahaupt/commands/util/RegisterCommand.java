package com.joaoahaupt.commands.util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class RegisterCommand {

    public static void register(JDA jda){

        jda.updateCommands()
                .addCommands(
                    Commands.slash("ping", "⛓️ Send forth a ping, and await the return of the pong!"),

                    Commands.slash("help", "📜 Unveil the ancient scrolls of wisdom, listing all known commands."),

                    Commands.slash("add", "📂 Forge a new tome to store thy tasks and deeds!")
                            .addOptions(
                                    new OptionData(OptionType.STRING, "name", "The name of thy new tome", true),
                                    new OptionData(OptionType.STRING, "description", "The lore of thy tome", true)
                            ),

                    Commands.slash("roll", "🎲 Cast the dices of fate and let fortune decide thy path!")
                            .addOptions(
                                    new OptionData(OptionType.INTEGER, "number", "The number of times the dices shall be cast", true).setRequiredRange(1, 100),
                                    new OptionData(OptionType.INTEGER, "faces", "The number of faces upon the dices", true)
                                            .addChoice("2", 2)
                                            .addChoice("4", 4)
                                            .addChoice("6", 6)
                                            .addChoice("8", 8)
                                            .addChoice("12", 12)
                                            .addChoice("20", 20)
                                            .addChoice("100", 100),
                                    new OptionData(OptionType.INTEGER, "modifier", "A boon or bane to alter thy fate")
                            ),

                        Commands.slash("annotation", "📜 Manage thy sacred annotations!")
                                .addSubcommands(
                                        new SubcommandData("create", "Create an annotation"),
//                                                .addOptions(
//                                                        new OptionData(OptionType.STRING, "folder", "The folder to store thy annotation", true),
//                                                        new OptionData(OptionType.STRING, "title", "The title of the annotation", true),
//                                                        new OptionData(OptionType.STRING, "description", "The lore of the annotation", true),
//                                                        new OptionData(OptionType.STRING, "tag", "The tag of the annotation", true)
//                                                                .addChoice()
//                                                                .addChoice()
//                                                                .addChoice()
//                                                                .addChoice()
//                                                                .addChoice(),
//                                                        new OptionData(OptionType.STRING, "attachments", "Attach thy scrolls", false)
//                                                )
                                        new SubcommandData("view", "List all annotations in a folder")
//                                        new SubcommandData("delete", "Delete an annotation")
//                                                .addOptions(
//                                                        new OptionData(OptionType.STRING, "folder", "The folder where thy annotation resides", true),
//                                                        new OptionData(OptionType.STRING, "title", "The title of thy annotation", true)
//                                                ),
//                                        new SubcommandData("move", "Move an annotation to another folder")
//                                                .addOptions(
//                                                        new OptionData(OptionType.STRING, "from", "The current folder", true),
//                                                        new OptionData(OptionType.STRING, "to", "The new folder", true),
//                                                        new OptionData(OptionType.STRING, "title", "The title of the annotation", true)
//                                                ),
//                                        new SubcommandData("rename", "Rename an annotation")
//                                                .addOptions(
//                                                        new OptionData(OptionType.STRING, "folder", "The folder where thy annotation resides", true),
//                                                        new OptionData(OptionType.STRING, "old_title", "The current title", true),
//                                                        new OptionData(OptionType.STRING, "new_title", "The new title", true)
//                                                )
                               )

                ).complete();

    }

}
