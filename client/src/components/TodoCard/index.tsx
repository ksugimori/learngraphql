import { graphql, useFragment } from "react-relay";

import styles from "./style.module.css";

import type { TodoCard_todo$key } from "./__generated__/TodoCard_todo.graphql";
import type React from "react";

type Props = {
  todoRef: TodoCard_todo$key;
};

export const TodoCard: React.FC<Props> = ({ todoRef }) => {
  const { title, isCompleted = false } = useFragment(
    graphql`
      fragment TodoCard_todo on Todo {
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
