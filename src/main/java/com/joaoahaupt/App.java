package com.joaoahaupt;

import com.joaoahaupt.commands.*;
import com.joaoahaupt.commands.util.RegisterCommand;
import com.joaoahaupt.listener.FolderSelectListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class App extends ListenerAdapter {
    public static void main(String[] args) {
        String token = System.getenv("TOKEN_SCRIBBLE");

        if (token != null) {
            try {
                JDA jda = JDABuilder.createDefault(token)
                        .addEventListeners(
                                new PingCommand(),
                                new HelpCommand(),
                                new FolderCommand(),
                                new RollCommand(),
                                new AnnotationCommand(),
                                new FolderSelectListener()
                        )
                        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                        .build()
                        .awaitReady();

                jda.getPresence().setActivity(Activity.customStatus("\uD83D\uDCDC Organizing forbidden knowledge..."));

                RegisterCommand.register(jda);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Token not found");
        }
    }
}