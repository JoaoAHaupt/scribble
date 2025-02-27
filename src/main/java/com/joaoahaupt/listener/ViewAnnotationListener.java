package com.joaoahaupt.listener;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.AnnotationDao;
import com.joaoahaupt.model.Annotation;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewAnnotationListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if(event.getComponentId().equals("folder_select_view")){

            Jdbi jdbi = DatabaseConnection.getJdbi();
            AnnotationDao annotationDao = jdbi.onDemand(AnnotationDao.class);

            List<Annotation> annotations =  annotationDao.selectAnnotationFolder(
                    Long.valueOf(event.getValues().get(0)
                    )
            );

            event.reply(annotations.toString()).queue();
        }
    }
}
