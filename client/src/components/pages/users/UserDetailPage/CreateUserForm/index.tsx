import { graphql, useMutation } from "react-relay";

import type { CreateUserFormMutation } from "./__generated__/CreateUserFormMutation.graphql";
import type { FormEventHandler } from "react";
import type React from "react";

type Props = {
  userId: string;
  onRefresh: () => void;
};

export const CreateUserForm: React.FC<Props> = ({ userId, onRefresh }) => {
  const [commitCreateTodoMutation] =
    useMutation<CreateUserFormMutation>(graphql`
      mutation CreateUserFormMutation($input: CreateTodoInput!) {
        createTodo(input: $input) {
          id
        }
      }
    `);

  const onSubmit = (title: string) => {
    commitCreateTodoMutation({
      variables: { input: { userId, title } },
      onCompleted: onRefresh,
    });
  };

  const handleSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);

    onSubmit(data.get("title")?.toString() ?? "");
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" placeholder="title" name="title" />
      <button>Submit</button>
    </form>
  );
};
