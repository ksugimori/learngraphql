import { graphql, useLazyLoadQuery } from "react-relay";
import { Link } from "react-router-dom";

import styles from "./style.module.css";
import { UserCard } from "../../../UserCard";

import type { UsersPageQuery } from "./__generated__/UsersPageQuery.graphql";
import type React from "react";

export const UsersPage: React.FC = () => {
  const { users } = useLazyLoadQuery<UsersPageQuery>(
    graphql`
      query UsersPageQuery {
        users {
          id
          ...UserCard_user
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
            <Link to={`/users/${user.id}`}>
              <UserCard userRef={user} />
            </Link>
          </li>
        ))}
      </ul>
    </section>
  );
};
