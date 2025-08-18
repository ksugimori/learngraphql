import { graphql, useFragment } from "react-relay";

import styles from "./style.module.css";
import { TodoListItem } from "../../../../TodoListItem";

import type { TodoListFragment$key } from "./__generated__/TodoListFragment.graphql";
import type React from "react";

type Props = {
  todosRef: TodoListFragment$key;
  reloadTodoList: () => void;
} & React.ComponentPropsWithoutRef<"ul">;

export const TodoList: React.FC<Props> = ({
  todosRef,
  reloadTodoList,
  ...rest
}) => {
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
          <TodoListItem todoRef={todoRef} reloadTodoList={reloadTodoList} />
        </li>
      ))}
    </ul>
  );
};
