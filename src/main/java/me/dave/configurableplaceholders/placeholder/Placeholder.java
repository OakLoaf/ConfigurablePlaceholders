package me.dave.configurableplaceholders.placeholder;

import org.jetbrains.annotations.Nullable;

public class Placeholder {
    private final String content;
    private JavaPlaceholder javaPlaceholder;
    private BedrockPlaceholder bedrockPlaceholder;

    public Placeholder(String content) {
        this.content = content;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    @Nullable
    public JavaPlaceholder getJavaPlaceholder() {
        return javaPlaceholder;
    }

    public void setJavaPlaceholder(@Nullable JavaPlaceholder javaPlaceholder) {
        this.javaPlaceholder = javaPlaceholder;
    }

    @Nullable
    public BedrockPlaceholder getBedrockPlaceholder() {
        return bedrockPlaceholder;
    }

    public void setBedrockPlaceholder(@Nullable BedrockPlaceholder bedrockPlaceholder) {
        this.bedrockPlaceholder = bedrockPlaceholder;
    }
}
