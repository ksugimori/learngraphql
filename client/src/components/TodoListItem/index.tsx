import { graphql, useFragment } from "react-relay";

import styles from "./style.module.css";

import type { TodoListItem_todo$key } from "./__generated__/TodoListItem_todo.graphql";
import type React from "react";

type Props = {
  todoRef: TodoListItem_todo$key;
};

export const TodoListItem: React.FC<Props> = ({ todoRef }) => {
  const { title, isCompleted = false } = useFragment(
    graphql`
      fragment TodoListItem_todo on Todo {
        title
        isCompleted
      }
    `,
    todoRef
  );

  return (
    <div className={styles.root}>
      <span className={styles.title}>{title}</span>
      <div className={styles.separator}></div>
      <div className={styles.buttons}>
        {/* TODO: アイコン化 */}
        <button disabled={isCompleted}>Finish</button>
        <button>Delete</button>
      </div>
    </div>
  );
};
