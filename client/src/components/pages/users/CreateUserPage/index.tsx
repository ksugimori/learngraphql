import { graphql, useMutation } from "react-relay";
import { useNavigate } from "react-router-dom";

import styles from "./style.module.css";

import type { CreateUserPageMutation } from "./__generated__/CreateUserPageMutation.graphql";
import type React from "react";

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
        navigate(`/users/${createUser?.id}`);
      },
    });
  };

  return (
    <section>
      <h2>Create User</h2>

      <form className={styles.form} onSubmit={onSubmit}>
        <input
          type="text"
          name="name"
          placeholder="name"
          aria-label="name-input"
          required
          autoComplete="off"
        />
        <button type="submit">Submit</button>
      </form>
    </section>
  );
};
