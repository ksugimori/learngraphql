import { graphql, useFragment } from "react-relay";

import styles from "./style.module.css";
import { TodoCard } from "../../../../TodoCard";

import type { TodoListFragment$key } from "./__generated__/TodoListFragment.graphql";
import type React from "react";

type Props = {
  todosRef: TodoListFragment$key;
} & React.ComponentPropsWithoutRef<"ul">;

export const TodoList: React.FC<Props> = ({ todosRef, ...rest }) => {
  const { todos } = useFragment(
    graphql`
      fragment TodoListFragment on User {
        todos {
          id
          ...TodoCard_todo
        }
      }
    `,
    todosRef
  );

  return (
    <ul className={styles.root} {...rest}>
      {todos.map((todoRef) => (
        <li key={todoRef.id}>
          <TodoCard todoRef={todoRef} />
        </li>
      ))}
    </ul>
  );
};
