import { graphql, useLazyLoadQuery } from "react-relay";

import styles from "./style.module.css";

import type { UsersPageQuery } from "./__generated__/UsersPageQuery.graphql";
import type React from "react";

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
    <section>
      <h2>Users</h2>
      <ul className={styles.list}>
        {users.map((u) => (
          <li key={u.id}>{u.name}</li>
        ))}
      </ul>
    </section>
  );
};
