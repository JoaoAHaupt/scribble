package com.joaoahaupt.listeners.annotation.select;

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
                    event.reply(":x: **Alas! No scrolls of wisdom dost thou possess.**").setEphemeral(true).queue();
                    return;
                }

                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle("📜 Sacred Scrolls of Knowledge")
                        .setColor(new Color(135, 67, 218))
                        .setFooter("Use these commands wisely, traveler to see your scrolls...", "https://cdn-icons-png.flaticon.com/512/3043/3043893.png");

                for (Annotation annotation : annotations) {
                    embedBuilder.addField("📝 " + annotation.getTitle() +" " +annotation.getTag() +" "+ annotation.getCreated(),
                            "**Description:** `" + annotation.getDescription() + "`\n" +"**Link to wisdom:** " + annotation.getLink()
                            , false);
                }

                event.
                        replyEmbeds(embedBuilder.build())
                        .addActionRow(
                                Button.primary("prev", "⬅️ Turn back time"),
                                Button.danger("close", "❌ Burn the scroll"),
                                Button.primary("next", "➡️ Advance the legend")
                        ).queue();

            } catch (NumberFormatException e) {
                event.reply(":x: **Woe upon thee!** The number thou hast entered is most unworthy.").setEphemeral(true).queue();
            }
        }
    }
}
