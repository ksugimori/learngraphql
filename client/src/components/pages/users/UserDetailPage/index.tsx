import { graphql, useLazyLoadQuery } from "react-relay";
import { useParams } from "react-router-dom";

import styles from "./style.module.css";
import { TodoCard } from "../../../TodoCard";

import type { UserDetailPageQuery } from "./__generated__/UserDetailPageQuery.graphql";
import type React from "react";

export const UserDetailPage: React.FC = () => {
  const { userId } = useParams();
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
    { userId: userId ?? "" }
  );

  if (user === null || user === undefined) {
    return (
      <section>
        <h2>User</h2>
        <p>User Not Found. ID: {userId}</p>
      </section>
    );
  }

  return (
    <section>
      <h2>User: {user.name}</h2>
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
