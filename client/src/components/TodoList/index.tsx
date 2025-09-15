import { graphql, usePaginationFragment } from "react-relay";

import styles from "./style.module.css";
import { ListItem } from "../ListItem";

import type { TodoListFragment$key } from "./__generated__/TodoListFragment.graphql";
import type React from "react";

import { TodoListItem } from "@/components/TodoListItem";

type Props = {
  todosRef: TodoListFragment$key;
  onChange: () => void;
} & React.ComponentPropsWithoutRef<"ul">;

export const TodoList: React.FC<Props> = ({ todosRef, onChange, ...rest }) => {
  const { data, loadNext, hasNext, isLoadingNext } = usePaginationFragment(
    graphql`
      fragment TodoListFragment on User
      @refetchable(queryName: "TodoListRefetchQuery")
      @argumentDefinitions(
        first: { type: "Int", defaultValue: 5 }
        after: { type: "String" }
      ) {
        todos(first: $first, after: $after) @connection(key: "TodoList_todos") {
          totalCount
          pageInfo {
            hasNextPage
            hasPreviousPage
          }
          edges {
            node {
              id
              ...TodoListItem_todo
            }
          }
        }
      }
    `,
    todosRef
  );

  const { todos } = data;

  const handleLoadMore = () => {
    if (hasNext && !isLoadingNext) {
      loadNext(5); // 次の5件を読み込み
    }
  };

  return (
    <ul className={styles.root} {...rest}>
      {todos.edges.map((todoRef) => (
        <li key={todoRef.node.id}>
          <TodoListItem todoRef={todoRef.node} onChange={onChange} />
        </li>
      ))}
      <div className={styles.loadMore}>
        {hasNext ? (
          <button
            type="button"
            onClick={handleLoadMore}
            disabled={isLoadingNext}
          >
            {isLoadingNext ? "Loading..." : "Load More..."}
          </button>
        ) : (
          <ListItem left="Total" right={`${todos.totalCount} tasks`} />
        )}
      </div>
    </ul>
  );
};
