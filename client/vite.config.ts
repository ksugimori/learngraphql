import react from "@vitejs/plugin-react";
import { defineConfig } from "vite";
import eslint from "vite-plugin-eslint";

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react({ babel: { plugins: ["relay"] } }),
    eslint({
      failOnError: false,
      include: ["src/**/*.ts", "src/**/*.tsx"],
    }),
    "import",
  ],
  rules: {
    "import/order": [
      "error",
      {
        groups: [
          "builtin", // Node.js 組み込みモジュール
          "external", // npm パッケージ
          "internal", // プロジェクト内のパス設定されたモジュール
          ["parent", "sibling"], // 親/兄弟ディレクトリのモジュール
          "index", // カレントディレクトリ
          "object", // オブジェクトによるインポート
          "type", // 型のインポート
        ],
        "newlines-between": "always", // グループ間に空行を入れる
        alphabetize: {
          order: "asc", // アルファベット順
          caseInsensitive: true, // 大文字小文字を区別しない
        },
      },
    ],
  },
});
