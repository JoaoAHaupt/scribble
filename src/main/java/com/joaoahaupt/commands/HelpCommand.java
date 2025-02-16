package com.joaoahaupt.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.awt.*;

public class HelpCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("help")) {
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("📜 Scribble commands")
                    .setDescription("Aqui está a lista de comandos disponíveis:\n\n" +
                            "**» Lista de comandos** (3)\n" +
                            "```" +
                            "/help        → sh\n" +
                            "/todo        → Gerencia sua lista de tarefas\n" +
                            "/note        → Cria e gerencia anotações\n" +
                            "```")
                    .setColor(Color.BLUE)
                    .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                    .setFooter("Criado por JoaoAHaupt", event.getJDA().getSelfUser().getAvatarUrl());

            StringSelectMenu menu = StringSelectMenu.create("help_menu")
                    .setPlaceholder("Selecione uma categoria...")
                    .addOption("📌 Tarefas", "todo")
                    .addOption("📝 Notas", "note")
                    .addOption("⚙️ Configuração", "config")
                    .build();

            event.replyEmbeds(embed.build())
                    .addActionRow(
                            Button.primary("prev", "⬅️"),
                            Button.danger("close", "❌"),
                            Button.secondary("info", "❓"),
                            Button.primary("next", "➡️")
                    )
                    .addActionRow(menu)
                    .queue();
        }
    }
}
