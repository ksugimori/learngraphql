import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import eslint from "vite-plugin-eslint";

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react({ babel: { plugins: ["relay"] } }),
    eslint({
      failOnError: false,
      include: ["src/**/*.ts", "src/**/*.tsx"],
    }),
  ],
});
