import type React from "react";
import { Link } from "react-router-dom";

export const HomePage: React.FC = () => {
  return (
    <main>
      <h1>Simple TODO</h1>
      <Link to="/users">Users</Link>
      <Link to="/users/new">Create User</Link>
    </main>
  );
};
