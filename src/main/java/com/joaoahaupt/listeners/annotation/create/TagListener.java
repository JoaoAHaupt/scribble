package com.joaoahaupt.listeners.annotation.create;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.AnnotationDao;
import com.joaoahaupt.model.Annotation;
import com.joaoahaupt.model.config.UserMemorySave;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class TagListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("tag_select")) {

            event.deferReply().queue();

            Annotation annotation = UserMemorySave.selectAnnotation(event.getUser().getIdLong());

            Jdbi jdbi = (DatabaseConnection.getJdbi());
            AnnotationDao annotationDao = jdbi.onDemand(AnnotationDao.class);


            annotation.setAuthor(event.getUser().getName());
            annotationDao.insertAnnotation(
                    annotation.getTitle(),
                    annotation.getDescription(),
                    new Date(),
                    new Date(),
                    annotation.getAuthor(),
                    event.getValues().get(0),
                    annotation.getLink(),
                    annotation.getFolderId()
            );

            event.getHook().sendMessage("Annotation: 📝`" + annotation.getTitle() + "` created!").queue();
        }
    }
}
