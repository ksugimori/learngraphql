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
      <main>
        <h1>User</h1>
        <p>userId = {userId} not found.</p>
      </main>
    );
  }

  return (
    <main>
      <h1>User: {user.name}</h1>
      <ul>
        {user.todos.map((u) => (
          <li key={u.id}>{u.title}</li>
        ))}
      </ul>
    </main>
  );
};
