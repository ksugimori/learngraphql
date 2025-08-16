import { graphql, useFragment } from "react-relay";

import styles from "./style.module.css";
import { Card } from "../Card";

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
    <Card>
      <span className={styles.title}>{title}</span>
      <button disabled={isCompleted}>完了</button>
      <button>削除</button>
    </Card>
  );
};
