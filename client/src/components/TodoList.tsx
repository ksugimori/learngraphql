import { graphql, useLazyLoadQuery } from "react-relay";
import type { TodoListQuery } from "./__generated__/TodoListQuery.graphql";

export function TodoList({ userId }: { userId: string }) {
  const data = useLazyLoadQuery<TodoListQuery>(
    graphql`
      query TodoListQuery($userId: ID!) {
        user(id: $userId) {
          id
          name
          todos {
            id
            title
            isCompleted
          }
        }
      }
    `,
    { userId }
  );

  return (
    <div>
      <h2>{data.user?.name}'s Todos</h2>
      <ul>
        {data.user?.todos.map((todo) => (
          <li key={todo.id}>{todo.title}</li>
        ))}
      </ul>
    </div>
  );
}
