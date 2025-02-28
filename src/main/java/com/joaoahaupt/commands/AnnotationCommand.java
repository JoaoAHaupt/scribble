package com.joaoahaupt.commands;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.AnnotationDao;
import com.joaoahaupt.dao.FolderDao;
import com.joaoahaupt.model.Folder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AnnotationCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("annotation")) return;

        String subcommand = event.getSubcommandName();

        if (subcommand == null) {
            event.reply("⚠️ **Traveler, thy command is unknown!**").queue();
            return;
        }

        Jdbi jdbi = DatabaseConnection.getJdbi();
        FolderDao folderDao = jdbi.onDemand(FolderDao.class);
        List<Folder> folders = folderDao.selectUserFolders(event.getUser().getIdLong());

        DatabaseConnection.closeConnection();

        if (folders.isEmpty()) {
            event.reply("⚠️ **Alas, thou hast no tome to inscribe upon! Create a folder first.**").queue();
            return;
        }

        StringSelectMenu.Builder selectMenuBuilderFolder = StringSelectMenu.create("general");
        for (Folder folder : folders) {
            selectMenuBuilderFolder.addOption(folder.getName(), String.valueOf(folder.getId()));
        }

        String replyMessage = "";

        switch (subcommand) {
            case "create":
                replyMessage ="\uD83D\uDCDC **Choose the tome where thy wisdom shall be scribed!**";
                selectMenuBuilderFolder.setId("folder_select_create");

                break;

            case "view":

                replyMessage ="\uD83D\uDD0D **Select the ancient tome to unveil its hidden secrets!**";
                selectMenuBuilderFolder.setId("folder_select_view");

                break;

            case "delete":

                replyMessage = "🔮 **Choose the ancient tome to seal away forbidden sorcery!**";
                selectMenuBuilderFolder.setId("folder_select_delete");
                break;

            default:
                event.reply("⚠️ **Traveler, thy command is most perplexing!**").queue();
                break;
        }

        event.reply(replyMessage)
                .setEphemeral(true)
                .addActionRow(selectMenuBuilderFolder.build())
                .queue();

    }
}
