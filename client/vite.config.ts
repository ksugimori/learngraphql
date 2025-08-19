import react from "@vitejs/plugin-react";
import eslint from "vite-plugin-eslint2";
import { defineConfig } from "vitest/config";

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react({ babel: { plugins: ["relay"] } }),
    eslint({
      cache: false,
      include: ["src/**/*.ts", "src/**/*.tsx"],
    }),
  ],
  resolve: {
    alias: {
      "@": "/src",
      "@/components": "/src/components",
      "@/pages": "/src/components/pages",
      "@/utils": "/src/utils",
      "@/types": "/src/types",
    },
  },
  test: {
    globals: true,
    environment: "jsdom",
    setupFiles: "./src/test/setup.ts",
  },
});
