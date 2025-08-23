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
        todos {
          id
          ...TodoListItem_todo
        }
      }
    `,
    todosRef
  );

  return (
    <ul className={styles.root} {...rest}>
      {todos.map((todoRef) => (
        <li key={todoRef.id}>
          <TodoListItem todoRef={todoRef} onChange={onChange} />
        </li>
      ))}
    </ul>
  );
};
