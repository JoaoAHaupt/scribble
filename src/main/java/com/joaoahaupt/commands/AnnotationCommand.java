package com.joaoahaupt.commands;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.AnnotationDao;
import com.joaoahaupt.dao.FolderDao;
import com.joaoahaupt.model.Folder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.Option;
import java.awt.*;
import java.util.List;

public class AnnotationCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equals("annotation")){

            String subcommand = event.getSubcommandName();

            if (subcommand != null) {

                Jdbi jdbi = DatabaseConnection.getJdbi();
                AnnotationDao annotationDao = jdbi.onDemand(AnnotationDao.class);
                FolderDao folderDao = jdbi.onDemand(FolderDao.class);


                switch (subcommand) {
                    case "create":
                        List<Folder> folders =  folderDao.selectUserFolders(event.getUser().getIdLong());

                        if (folders.isEmpty()) {
                            event.reply("No folders available. Please create a folder first.").queue();
                            return;
                        }

                        StringSelectMenu.Builder selectMenuBuilderFolder = StringSelectMenu.create("folder_select");
                        for (Folder folder : folders) {
                            selectMenuBuilderFolder.addOption(folder.getName(), String.valueOf(folder.getId()));
                        }

                        event.reply("Choose the folder for your annoatation")
                                .setEphemeral(true)
                                .addActionRow(selectMenuBuilderFolder.build())
                                .queue();


                        break;

                    case "view":
                        List<Folder> folderss =  folderDao.selectUserFolders(event.getUser().getIdLong());

                        if (folderss.isEmpty()) {
                            event.reply("No folders available. Please create a folder first.").queue();
                            return;
                        }

                        StringSelectMenu.Builder selectMenuBuilderFolderView = StringSelectMenu.create("folder_select_view");
                        for (Folder folder : folderss) {
                            selectMenuBuilderFolderView.addOption(folder.getName(), String.valueOf(folder.getId()));
                        }

                        event.reply("Choose the folder to see your")
                                .setEphemeral(true)
                                .addActionRow(selectMenuBuilderFolderView.build())
                                .queue();

                        break;

                    default:
                        event.reply("Unknown subcommand!").queue();
                        break;
                }
            }
        }
    }
}
