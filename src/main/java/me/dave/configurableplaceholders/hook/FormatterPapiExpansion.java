package me.dave.configurableplaceholders.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import me.dave.configurableplaceholders.placeholder.BedrockPlaceholder;
import me.dave.configurableplaceholders.placeholder.JavaPlaceholder;
import me.dave.configurableplaceholders.placeholder.Placeholder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FormatterPapiExpansion extends PlaceholderExpansion {

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        Placeholder placeholder = new Placeholder();
        JavaPlaceholder.Builder javaPlaceholderBuilder = new JavaPlaceholder.Builder();
        BedrockPlaceholder.Builder bedrockPlaceholderBuilder = new BedrockPlaceholder.Builder();

        String[] args = params.split("_");
        for (String arg : args) {
            String[] mapping = arg.split(":");
            if (mapping.length >= 2) {
                String key = mapping[0];
                String value = mapping[1];

                switch (key) {
                    case "default" -> {
                        placeholder.setContent(value);
                    }
                    case "java" -> {
                        javaPlaceholderBuilder.setContent(value);
                    }
                    case "java-rp" -> {
                        javaPlaceholderBuilder.setRp(value);
                    }
                    case "java-no-rp" -> {
                        javaPlaceholderBuilder.setNoRp(value);
                    }
                    case "bedrock" -> {
                        bedrockPlaceholderBuilder.setContent(value);
                    }
                }
            }
        }

        placeholder.setJavaPlaceholder(javaPlaceholderBuilder.build());
        placeholder.setBedrockPlaceholder(bedrockPlaceholderBuilder.build());

        return placeholder.parse(player);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "cpf";
    }

    @Override
    public @NotNull String getAuthor() {
        return ConfigurablePlaceholders.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return ConfigurablePlaceholders.getInstance().getDescription().getVersion();
    }
}
