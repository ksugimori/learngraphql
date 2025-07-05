# GraphQL 学習メモ

GraphQL についての学習用リポジトリ。

## Spring for GraphQL + Spring Boot での基本的な流れ

- GraphQL スキーマ定義は src/resources/*.graphqls
- コントローラーのメソッドに `@QueryMapping` や `@MutationMapping` を付与すると query や mutation とマッピングされる

## GraphiQL

起動後に以下の URL でアクセス可能

http://localhost:8080/graphiql.html