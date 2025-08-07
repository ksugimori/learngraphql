import { graphql, useLazyLoadQuery } from "react-relay";
import { useParams } from "react-router-dom";

import type { TodoListQuery } from "./__generated__/TodoListQuery.graphql";

export function TodoList() {
  const { userId } = useParams<{ userId: string }>();

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
    { userId: userId! }
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
