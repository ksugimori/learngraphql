import { graphql, useFragment, useMutation } from "react-relay";

import styles from "./style.module.css";

import type { TodoListItem_todo$key } from "./__generated__/TodoListItem_todo.graphql";
import type { TodoListItemDeleteMutation } from "./__generated__/TodoListItemDeleteMutation.graphql";
import type { TodoListItemUpdateMutation } from "./__generated__/TodoListItemUpdateMutation.graphql";
import type React from "react";

const deleteMutation = graphql`
  mutation TodoListItemDeleteMutation($id: ID!) {
    deleteTodo(id: $id)
  }
`;

const updateMutation = graphql`
  mutation TodoListItemUpdateMutation($input: UpdateTodoInput!) {
    updateTodo(input: $input) {
      id
    }
  }
`;

type Props = {
  todoRef: TodoListItem_todo$key;
  reloadTodoList: () => void;
};

export const TodoListItem: React.FC<Props> = ({ todoRef, reloadTodoList }) => {
  const {
    id,
    title,
    isCompleted = false,
  } = useFragment(
    graphql`
      fragment TodoListItem_todo on Todo {
        id
        title
        isCompleted
      }
    `,
    todoRef
  );

  const [commitDelete] =
    useMutation<TodoListItemDeleteMutation>(deleteMutation);
  const [commitUpdate] =
    useMutation<TodoListItemUpdateMutation>(updateMutation);

  const handleDelete = () => {
    commitDelete({ variables: { id } });
    reloadTodoList();
  };

  const handleComplete = () => {
    commitUpdate({
      variables: { input: { id, isCompleted: true } },
    });
    reloadTodoList();
  };

  return (
    <div className={styles.root}>
      <span className={styles.title}>{title}</span>
      <div className={styles.separator}></div>
      <div className={styles.buttons}>
        <button onClick={handleComplete} disabled={isCompleted}>
          Complete
        </button>
        <button onClick={handleDelete}>Delete</button>
      </div>
    </div>
  );
};
