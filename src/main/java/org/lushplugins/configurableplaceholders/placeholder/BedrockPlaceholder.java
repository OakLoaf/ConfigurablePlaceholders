package org.lushplugins.configurableplaceholders.placeholder;

public record BedrockPlaceholder(String content) {
    public static class Builder {
        private String content;

        public void setContent(String content) {
            this.content = content;
        }

        public BedrockPlaceholder build() {
            return new BedrockPlaceholder(content);
        }
    }
}
