package com.joaoahaupt.listeners.annotation.delete;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.AnnotationDao;
import com.joaoahaupt.model.Annotation;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FolderSelectDeleteAnnotationListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if(!event.getComponentId().equals("folder_select_delete")) return;

        Jdbi jdbi = DatabaseConnection.getJdbi();
        AnnotationDao annotationDao = jdbi.onDemand(AnnotationDao.class);

        List<Annotation> annotationList =
                annotationDao.
                        selectAnnotationFolder(
                                Long.valueOf(event.getValues().get(0)
                        )
                );

        DatabaseConnection.closeConnection();

        if (annotationList.isEmpty()) {
            event.reply("⚠️ **Alas! No scrolls remain to be burned...**").setEphemeral(true).queue();
            return;
        }

        StringSelectMenu.Builder stringSelectMenu = StringSelectMenu.create("annotation_list_on_delete");

        for (Annotation annotation: annotationList){
            stringSelectMenu.addOption(annotation.getTitle(), String.valueOf(annotation.getId()));
        }

        event.reply("🔥 **Choose the cursed scroll to cast into oblivion!**")
                .addActionRow(stringSelectMenu.build())
                .setEphemeral(true)
                .queue();

    }

}
