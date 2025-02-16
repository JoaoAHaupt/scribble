package com.joaoahaupt.commands;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.FolderDao;
import com.joaoahaupt.dao.UserDao;
import com.joaoahaupt.model.Folder;
import com.joaoahaupt.model.User;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FolderCommand extends ListenerAdapter {

    @SneakyThrows
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("add")) {

            Long id = event.getUser().getIdLong();
            String name = Objects.requireNonNull(event.getOption("name")).getAsString();
            String description = Objects.requireNonNull(event.getOption("description")).getAsString();

            Jdbi jdbi = DatabaseConnection.getJdbi();

            UserDao userDao = jdbi.onDemand(UserDao.class);


            if (userDao.getUserById(id) == null) {
                userDao.insertUser(id, event.getUser().getName());
            }

            FolderDao folderDao = jdbi.onDemand(FolderDao.class);
            folderDao.insertFolder(name, description, id);

            event.reply("📁 Pasta `" + name + "` criada com sucesso!")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
