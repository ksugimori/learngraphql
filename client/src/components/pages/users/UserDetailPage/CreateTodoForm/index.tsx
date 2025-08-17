import { graphql, useMutation } from "react-relay";

import type { CreateTodoFormMutation } from "./__generated__/CreateTodoFormMutation.graphql";
import type { FormEventHandler } from "react";
import type React from "react";

type Props = {
  userId: string;
  onComplete: () => void;
};

const mutation = graphql`
  mutation CreateTodoFormMutation($input: CreateTodoInput!) {
    createTodo(input: $input) {
      id
    }
  }
`;

export const CreateTodoForm: React.FC<Props> = ({ userId, onComplete }) => {
  const [commitMutation] = useMutation<CreateTodoFormMutation>(mutation);

  const handleSubmit: FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();

    const form = e.currentTarget;
    const data = new FormData(form);
    const title = data.get("title")?.toString() ?? "";

    commitMutation({
      variables: { input: { userId, title } },
      onCompleted: () => {
        form.reset();
        onComplete();
      },
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" placeholder="title" name="title" />
      <button>Submit</button>
    </form>
  );
};
