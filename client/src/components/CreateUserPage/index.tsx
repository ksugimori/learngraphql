import type React from "react";

import { graphql, useMutation } from "react-relay";
import { useNavigate } from "react-router-dom";
import type { CreateUserPageMutation } from "./__generated__/CreateUserPageMutation.graphql";
import styles from "./style.module.css";

export const CreateUserPage: React.FC = () => {
  const navigate = useNavigate();
  const [commitMutation] = useMutation<CreateUserPageMutation>(graphql`
    mutation CreateUserPageMutation($input: CreateUserInput!) {
      createUser(input: $input) {
        id
      }
    }
  `);

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    const name = formData.get("name")?.toString() ?? "";

    commitMutation({
      variables: { input: { name } },
      onCompleted: ({ createUser }) => {
        navigate(`/users/${createUser.id}`);
      },
    });
  };

  return (
    <main>
      <h1>Create User</h1>

      <form className={styles.form} onSubmit={onSubmit}>
        <label>
          name:
          <input type="text" name="name" />
        </label>
        <button type="submit">Submit</button>
      </form>
    </main>
  );
};
