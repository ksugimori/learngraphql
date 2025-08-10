import js from '@eslint/js'
import importPlugin from 'eslint-plugin-import'
import reactHooks from 'eslint-plugin-react-hooks'
import reactRefresh from 'eslint-plugin-react-refresh'
import { globalIgnores } from 'eslint/config'
import globals from 'globals'
import tseslint from 'typescript-eslint'

export default tseslint.config([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    plugins: {
      import: importPlugin,
    },
    extends: [
      js.configs.recommended,
      tseslint.configs.recommended,
      reactHooks.configs['recommended-latest'],
      reactRefresh.configs.vite,
    ],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
    },
    rules: {
      'import/order': [
        'error',
        {
          groups: [
            'builtin', // Node.js 組み込みモジュール
            'external', // npm パッケージ
            'internal', // プロジェクト内のパス設定されたモジュール
            ['parent', 'sibling'], // 親/兄弟ディレクトリのモジュール
            'index', // カレントディレクトリ
            'object', // オブジェクトによるインポート
            'type', // 型のインポート
          ],
          'newlines-between': 'always', // グループ間に空行を入れる
          alphabetize: {
            order: 'asc', // アルファベット順
            caseInsensitive: true, // 大文字小文字を区別しない
          },
        },
      ],
    },
  },
])
