package com.joaoahaupt.listener;

import com.joaoahaupt.config.DatabaseConnection;
import com.joaoahaupt.dao.AnnotationDao;
import com.joaoahaupt.model.Annotation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.List;

public class ViewAnnotationListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("folder_select_view")) {
            Jdbi jdbi = DatabaseConnection.getJdbi();
            AnnotationDao annotationDao = jdbi.onDemand(AnnotationDao.class);

            try {
                long folderId = Long.parseLong(event.getValues().get(0));
                List<Annotation> annotations = annotationDao.selectAnnotationFolder(folderId);

                if (annotations.isEmpty()) {
                    event.reply(":x: **You don't have any annotation.**").setEphemeral(true).queue();
                    return;
                }

                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle("📜 Annotations")
                        .setColor(new Color(135, 67, 218))
                        .setFooter("Use the commands to .", "https://cdn-icons-png.flaticon.com/512/3043/3043893.png");

                for (Annotation annotation : annotations) {
                    embedBuilder.addField("📝 " + annotation.getTitle() +" " +annotation.getTag() +" "+ annotation.getCreated(),
                            "**Description:** `" + annotation.getDescription() + "`\n" +"**Link:** `" + annotation.getLink() + "`"
                            , false);
                }

                event.
                        replyEmbeds(embedBuilder.build())
                        .addActionRow(
                                Button.primary("prev", "⬅️"),
                                Button.danger("close", "❌"),
                                Button.primary("next", "➡️")
                        ).queue();

            } catch (NumberFormatException e) {
                event.reply(":x: **Errr:** Invalid ID.").setEphemeral(true).queue();
            }
        }
    }
}
