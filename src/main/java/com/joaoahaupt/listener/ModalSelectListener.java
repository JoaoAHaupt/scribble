package com.joaoahaupt.listener;

import com.joaoahaupt.model.User;
import com.joaoahaupt.model.config.UserMemorySave;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

public class ModalSelectListener extends ListenerAdapter {
    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("create_annotation")) {
            String title = event.getValue("title").getAsString();
            String description = event.getValue("description").getAsString();

            System.out.println("Título: " + title);
            System.out.println("Descrição: " + description);

            StringSelectMenu.Builder tagMenuBuilder = StringSelectMenu.create("tag_select")
                    .addOption("📚 Studies", "studies")
                    .addOption("🎯 Work", "work")
                    .addOption("🎮 Leisure", "leisure")
                    .addOption("🏋️ Health & Fitness", "health")
                    .addOption("💡 Ideas & Creativity", "ideas")
                    .addOption("🛒 Shopping & Errands", "shopping")
                    .addOption("📅 Appointments & Meetings", "appointments")
                    .addOption("🎵 Music & Art", "music_art")
                    .addOption("✈️ Travel & Exploration", "travel")
                    .addOption("🛠️ Projects & DIY", "projects")
                    .addOption("🍽️ Food & Recipes", "food")
                    .addOption("📖 Books & Reading", "books")
                    .addOption("🎥 Movies & TV Shows", "movies_tv")
                    .addOption("🐾 Pets & Animals", "pets")
                    .addOption("❤️ Personal & Self-care", "personal");


            event
                    .reply("Choose a tag for your folder")
                    .addActionRow(tagMenuBuilder.build())
                    .setEphemeral(true)
                    .queue();
        }
    }


}
