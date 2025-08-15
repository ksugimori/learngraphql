import { graphql, useLazyLoadQuery } from "react-relay";
import { useParams } from "react-router-dom";

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
            title
            isCompleted
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
      <ul>
        {user.todos.map((u) => (
          <li key={u.id}>{u.title}</li>
        ))}
      </ul>
    </section>
  );
};
