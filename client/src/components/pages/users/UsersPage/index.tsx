import { graphql, useLazyLoadQuery } from "react-relay";
import { Link } from "react-router-dom";

import styles from "./style.module.css";

import type { UsersPageQuery } from "./__generated__/UsersPageQuery.graphql";
import type React from "react";

import { ListItem } from "@/components/ListItem";

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
        {users.map((user) => (
          <li key={user.id}>
            <ListItem
              left={user.name}
              right={<Link to={`/users/${user.id}`}>Detail</Link>}
            />
          </li>
        ))}
      </ul>
    </section>
  );
};
