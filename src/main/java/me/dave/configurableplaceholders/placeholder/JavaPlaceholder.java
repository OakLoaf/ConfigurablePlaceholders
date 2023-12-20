package me.dave.configurableplaceholders.placeholder;

public record JavaPlaceholder(String content, String rp, String noRp) {
    public static class Builder {
        private String content;
        private String rp;
        private String noRp;

        public void setContent(String content) {
            this.content = content;
        }

        public void setRp(String rp) {
            this.rp = rp;
        }

        public void setNoRp(String noRp) {
            this.noRp = noRp;
        }

        public JavaPlaceholder build() {
            return new JavaPlaceholder(content, rp, noRp);
        }
    }
}
