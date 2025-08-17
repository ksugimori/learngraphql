import { useEffect } from "react";
import {
  graphql,
  useQueryLoader,
  usePreloadedQuery,
  type PreloadedQuery,
} from "react-relay";
import { useParams } from "react-router-dom";

import { CreateUserForm } from "./CreateUserForm";
import { TodoList } from "./TodoList";

import type { UserDetailPageQuery } from "./__generated__/UserDetailPageQuery.graphql";
import type React from "react";

const query = graphql`
  query UserDetailPageQuery($userId: ID!) {
    user(id: $userId) {
      name
      ...TodoListFragment
    }
  }
`;

type UserDetailContentProps = {
  queryRef: PreloadedQuery<UserDetailPageQuery>;
  userId: string;
  onRefresh: () => void;
};

const UserDetailContent: React.FC<UserDetailContentProps> = ({
  queryRef,
  userId,
  onRefresh,
}) => {
  const { user } = usePreloadedQuery<UserDetailPageQuery>(query, queryRef);

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

      <h3>Create Todo</h3>
      <CreateUserForm userId={userId} onRefresh={onRefresh} />

      <h3>All Todos</h3>
      <TodoList todosRef={user} />
    </section>
  );
};

export const UserDetailPage: React.FC = () => {
  const { userId = "empty" } = useParams();

  const [queryRef, loadQuery, disposeQuery] =
    useQueryLoader<UserDetailPageQuery>(query);

  useEffect(() => {
    loadQuery({ userId });
  }, [userId, loadQuery]);

  const handleRefresh = () => {
    disposeQuery();
    loadQuery({ userId }, { fetchPolicy: "network-only" });
  };

  // queryRef が null の場合はローディング表示
  if (!queryRef) {
    return <div>Loading...</div>;
  }

  return (
    <UserDetailContent
      queryRef={queryRef}
      userId={userId}
      onRefresh={handleRefresh}
    />
  );
};
