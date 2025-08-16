import { graphql, useLazyLoadQuery, useMutation } from "react-relay";
import { useParams } from "react-router-dom";

import styles from "./style.module.css";
import { TodoCard } from "../../../TodoCard";

import type { UserDetailPageCreateTodoMutation } from "./__generated__/UserDetailPageCreateTodoMutation.graphql";
import type { UserDetailPageQuery } from "./__generated__/UserDetailPageQuery.graphql";
import type React from "react";

export const UserDetailPage: React.FC = () => {
  const { userId = "empty" } = useParams();
  const { user } = useLazyLoadQuery<UserDetailPageQuery>(
    graphql`
      query UserDetailPageQuery($userId: ID!) {
        user(id: $userId) {
          name
          todos {
            id
            ...TodoCard_todo
          }
        }
      }
    `,
    { userId }
  );

  const [commitCreateTodoMutation] =
    useMutation<UserDetailPageCreateTodoMutation>(graphql`
      mutation UserDetailPageCreateTodoMutation($input: CreateTodoInput!) {
        createTodo(input: $input) {
          id
        }
      }
    `);

  if (user === null || user === undefined) {
    return (
      <section>
        <h2>User</h2>
        <p>User Not Found. ID: {userId}</p>
      </section>
    );
  }

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    const title = formData.get("title")?.toString() ?? "";

    commitCreateTodoMutation({
      variables: { input: { userId, title } },
      onCompleted: ({ createTodo }) => {
        console.log(`create Todo: id = ${createTodo?.id}`);
        // TODO: リロードさせる
      },
    });
  };

  return (
    <section>
      <h2>User: {user.name}</h2>

      <h3>Create Todo</h3>
      <form onSubmit={onSubmit}>
        <input type="hidden" name="userId" value={userId} />
        <input type="text" placeholder="title" name="title" />
        <button>Submit</button>
      </form>

      <h3>All Todos</h3>
      <ul className={styles.cardList}>
        {user.todos.map((todoRef) => (
          <li key={todoRef.id}>
            <TodoCard todoRef={todoRef} />
          </li>
        ))}
      </ul>
    </section>
  );
};
