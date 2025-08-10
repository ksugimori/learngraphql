import type React from "react";
import { graphql, useLazyLoadQuery } from "react-relay";
import type { UsersPageQuery } from "./__generated__/UsersPageQuery.graphql";

import styles from "./style.module.css";

export const UsersPage: React.FC = () => {
  const { users } = useLazyLoadQuery<UsersPageQuery>(
    graphql`
      query UsersPageQuery {
        users {
          id
          name
        }
      }
    `,
    {}
  );

  return (
    <main>
      <h1>Users</h1>
      <ul className={styles.list}>
        {users.map((u) => (
          <li key={u.id}>{u.name}</li>
        ))}
      </ul>
    </main>
  );
};
