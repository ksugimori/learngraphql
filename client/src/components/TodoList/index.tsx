import { graphql, useFragment } from "react-relay";

import styles from "./style.module.css";

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
    </ul>
  );
};
