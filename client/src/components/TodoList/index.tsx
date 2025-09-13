import { graphql, useFragment } from "react-relay";

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
  const { todos } = useFragment(
    graphql`
      fragment TodoListFragment on User {
        todos(first: 5) {
          totalCount
          pageInfo {
            hasNextPage
            hasPreviousPage
          }
          edges {
            cursor
            node {
              ...TodoListItem_todo
            }
          }
        }
      }
    `,
    todosRef
  );

  return (
    <ul className={styles.root} {...rest}>
      {todos.edges.map((todoRef) => (
        <li key={todoRef.cursor}>
          <TodoListItem todoRef={todoRef.node} onChange={onChange} />
        </li>
      ))}
      <div className={styles.loadMore}>
        {todos.pageInfo.hasNextPage ? (
          <button type="button">Load More...</button>
        ) : (
          <ListItem left="Total" right={`${todos.totalCount} tasks`} />
        )}
      </div>
    </ul>
  );
};
