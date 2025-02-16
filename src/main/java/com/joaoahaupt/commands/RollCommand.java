package com.joaoahaupt.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RollCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("roll")) {

            int numbers = event.getOption("number").getAsInt();
            int faces = event.getOption("faces").getAsInt();

            int modifier = event.getOption("modifier") != null ? event.getOption("modifier").getAsInt() : 0;

            if (numbers <= 0 || faces <= 0) {
                event.reply("❌ Both `number` and `faces` must be greater than 0.").queue();
                return;
            }

            int[] results = new int[numbers];

            for (int i = 0; i < numbers; i++) {
                results[i] = ThreadLocalRandom.current().nextInt(1, faces + 1) + modifier;
            }

            if(numbers>1){
                event.reply("🎲 You rolled: " + Arrays.toString(results) + " = " + Arrays.stream(results).sum()) .queue();
            }
            else {
                event.reply("🎲 You rolled: " + Arrays.toString(results)).queue();
            }
        }
    }
}
