import { graphql, useFragment } from "react-relay";

import { Card } from "../Card";

import type { UserCard_user$key } from "./__generated__/UserCard_user.graphql";
import type React from "react";

type Props = {
  userRef: UserCard_user$key;
};

export const UserCard: React.FC<Props> = ({ userRef }) => {
  const { name } = useFragment(
    graphql`
      fragment UserCard_user on User {
        name
      }
    `,
    userRef
  );

  return <Card>{name}</Card>;
};
