package com.joaoahaupt.listener;

import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

public class FolderSelectListener extends ListenerAdapter {

    @Override
    public void onGenericSelectMenuInteraction(@NotNull GenericSelectMenuInteractionEvent event) {
        if (event.getComponentId().equals("folder_select")) {

            String selectedFolder = (String) event.getValues().get(0);

            TextInput title = TextInput.create("title", "Title", TextInputStyle.SHORT)
                    .setPlaceholder("Title of the annotation")
                    .setMinLength(10)
                    .setMaxLength(100)
                    .build();

            TextInput description = TextInput.create("description", "Description", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Description of the annotation")
                    .setMinLength(30)
                    .setMaxLength(1000)
                    .build();

            TextInput tags = TextInput.create("tags", "Tags", TextInputStyle.SHORT)
                    .setPlaceholder("Enter one of the following tags: 📚 Studies, 🎯 Work, 🎮 Leisure, 🏋️ Health, 💡 Ideas")
                    .setMinLength(1)
                    .setMaxLength(100)
                    .build();

            Modal modal = Modal.create("create_annotation", "Create Annotation")
                    .addComponents(
                            ActionRow.of(title),
                            ActionRow.of(description),
                            ActionRow.of(tags)
                    )
                    .build();

            // Enviando o modal para o usuário
            event.replyModal(modal).queue();
        }
    }
}
