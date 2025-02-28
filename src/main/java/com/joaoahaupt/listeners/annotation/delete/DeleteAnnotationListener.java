package com.joaoahaupt.listeners.annotation.delete;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.AnnotationDao;
import com.joaoahaupt.model.Annotation;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DeleteAnnotationListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if(!event.getComponentId().equals("annotation_list_on_delete")) return;

        Jdbi jdbi = DatabaseConnection.getJdbi();
        AnnotationDao annotationDao = jdbi.onDemand(AnnotationDao.class);

        annotationDao.deleteAnnotation(Long.valueOf(event.getValues().get(0)));
        DatabaseConnection.closeConnection();

        event.reply("Annotation delete").queue();

    }
}
