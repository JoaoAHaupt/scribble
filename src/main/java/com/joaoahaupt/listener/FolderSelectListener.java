package com.joaoahaupt.listener;

import com.joaoahaupt.model.Annotation;
import com.joaoahaupt.model.config.UserMemorySave;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

public class FolderSelectListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("folder_select")) {

            Annotation annotation = new Annotation();
            annotation.setFolderId(Long.valueOf(event.getValues().get(0)));

            UserMemorySave.insertAnnotation(
                    event.getUser().getIdLong(),
                    annotation
            );

            TextInput title = TextInput.create("title", "Title", TextInputStyle.SHORT)
                    .setPlaceholder("Title of the annotation")
                    .setMinLength(10)
                    .setMaxLength(100)
                    .build();

            TextInput link = TextInput.create("link", "Link", TextInputStyle.SHORT)
                    .setPlaceholder("Link of the annotation")
                    .setMinLength(10)
                    .setMaxLength(300)
                    .build();

            TextInput description = TextInput.create("description", "Description", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Description of the annotation")
                    .setMinLength(30)
                    .setMaxLength(1000)
                    .build();


            Modal modal = Modal.create("create_annotation", "Create Annotation")
                    .addComponents(
                            ActionRow.of(title),
                            ActionRow.of(link),
                            ActionRow.of(description)

                    )
                    .build();

            event.replyModal(modal).queue();
        }
    }
}
