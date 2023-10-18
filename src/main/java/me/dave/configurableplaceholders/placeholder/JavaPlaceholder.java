package me.dave.configurableplaceholders.placeholder;

import org.jetbrains.annotations.Nullable;

public class JavaPlaceholder {
    private final String content;
    private final String rp;
    private final String noRp;

    public JavaPlaceholder(String content) {
        this.content = content;
        this.rp = null;
        this.noRp = null;
    }

    public JavaPlaceholder(String content, @Nullable String rp, @Nullable String noRp) {
        this.content = content;
        this.rp = rp;
        this.noRp = noRp;
    }

    public String getContent() {
        return content;
    }

    @Nullable
    public String getRp() {
        return rp;
    }

    @Nullable
    public String getNoRp() {
        return noRp;
    }
}
